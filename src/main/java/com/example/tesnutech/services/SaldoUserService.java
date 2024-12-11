package com.example.tesnutech.services;

import com.example.tesnutech.mapper.SaldoUserMapper;
import com.example.tesnutech.mapper.UserMapper;
import com.example.tesnutech.model.SaldoUserModel;
import com.example.tesnutech.model.UserModel;
import com.example.tesnutech.utils.AuthorizatoinValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class SaldoUserService {

    @Autowired
    SaldoUserMapper saldoUserMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    AuthorizatoinValidationUtil authorizatoinValidationUtil;

    public ResponseEntity<Map<String, Object>> cekSaldo(String authorization){
        Map<String, Object> response = new HashMap<>();
        try{
            int statusAuth = authorizatoinValidationUtil.statusCodeAuth(authorization);
            if(statusAuth == HttpStatus.UNAUTHORIZED.value()){
                response.put("statusCode", HttpStatus.UNAUTHORIZED.value());
                response.put("message", "unauthorized atau token kadaluwarsa.");
            } else {
                Map<String, Object> data = authorizatoinValidationUtil.dataJsonAuth(authorization);

                UserModel dataUser = userMapper.findUser(
                        data.get("email").toString(),
                        data.get("password").toString()
                );

                SaldoUserModel dataBalance = saldoUserMapper.findSaldoUser(dataUser == null ? null : dataUser.getIdUser());

                if (dataBalance == null) {
                    response.put("statusCode", HttpStatus.NOT_FOUND.value());
                    response.put("message", "data tidak ditemukan.");
                } else {
                    response.put("statusCode", HttpStatus.OK.value());
                    response.put("message", "sukses.");
                    response.put("data", dataBalance);
                }
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(response);
        }
    }

}
