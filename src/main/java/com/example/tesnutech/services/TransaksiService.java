package com.example.tesnutech.services;

import com.example.tesnutech.mapper.HistoryTransaksiMapper;
import com.example.tesnutech.mapper.PpobMerchantMapper;
import com.example.tesnutech.mapper.SaldoUserMapper;
import com.example.tesnutech.mapper.UserMapper;
import com.example.tesnutech.model.PpobMerchantModel;
import com.example.tesnutech.model.SaldoUserModel;
import com.example.tesnutech.model.UserModel;
import com.example.tesnutech.pojos.HistoryTransaksiPojo;
import com.example.tesnutech.pojos.PpobMerchantPojo;
import com.example.tesnutech.utils.AuthorizatoinValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class TransaksiService {

    @Autowired
    PpobMerchantMapper ppobMerchantMapper;

    @Autowired
    HistoryTransaksiMapper historyTransaksiMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    SaldoUserMapper saldoUserMapper;

    @Autowired
    AuthorizatoinValidationUtil authorizatoinValidationUtil;

    public ResponseEntity<Map<String, Object>> pembayaran(String authorization, HistoryTransaksiPojo param){
        Map<String, Object> response = new HashMap<>();
        try{
            int statusAuth = authorizatoinValidationUtil.statusCodeAuth(authorization);
            if(statusAuth == HttpStatus.UNAUTHORIZED.value()){
                response.put("statusCode", HttpStatus.UNAUTHORIZED.value());
                response.put("message", "unauthorized atau token kadaluwarsa.");
            } else {

                PpobMerchantPojo ppobMerchantPojo = new PpobMerchantPojo();
                ppobMerchantPojo.setServiceCode(param.getServiceCode());

                PpobMerchantModel ppobMerchant = ppobMerchantMapper.getMerchant(ppobMerchantPojo);

                if(ppobMerchant == null){
                    response.put("statusCode", HttpStatus.NOT_FOUND.value());
                    response.put("message", "produk belum tersedia.");
                }else{
                    Map<String, Object> data = authorizatoinValidationUtil.dataJsonAuth(authorization);
                    UserModel dataUser = userMapper.findUser(
                            data.get("email").toString(),
                            data.get("password").toString()
                    );

                    SaldoUserModel balance = saldoUserMapper.findSaldoUser(dataUser.getIdUser());

                    if(balance == null){
                        response.put("statusCode", HttpStatus.NOT_FOUND.value());
                        response.put("message", "data saldo pengguna null.");
                    }else{
                        if(balance.getBalance() < ppobMerchant.getPrice()){
                            response.put("statusCode", HttpStatus.BAD_REQUEST.value());
                            response.put("message", "saldo tidak cukup.");
                        } else {
                            param.setIdHistoryTransaksi(UUID.randomUUID().toString());
                            param.setIdUser(dataUser.getIdUser());
                            param.setIdPpobMerchant(ppobMerchant.getIdPpobMerchant());
                            param.setServiceCode(ppobMerchant.getServiceCode());
                            param.setServiceName(ppobMerchant.getServiceName());
                            param.setJenis(ppobMerchant.getJenis());
                            param.setTransactionType("PAYMENT");
                            param.setTotalAmount(ppobMerchant.getPrice());

                            historyTransaksiMapper.addNewTransaksi(param);

                            response.put("statusCode", HttpStatus.OK.value());
                            response.put("message", "sukses.");
                            response.put("data", param);
                        }
                    }
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
