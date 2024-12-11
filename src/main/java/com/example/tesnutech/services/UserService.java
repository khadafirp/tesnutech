package com.example.tesnutech.services;

import com.example.tesnutech.mapper.UserMapper;
import com.example.tesnutech.model.UserModel;
import com.example.tesnutech.pojos.HistoryLoginPojo;
import com.example.tesnutech.pojos.LoginPojo;
import com.example.tesnutech.pojos.UserPojo;
import com.example.tesnutech.utils.EmailValidationUtil;
import com.example.tesnutech.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private UserMapper userMapper;

    @Value("${security.jwt.expiration-time}")
    String expiredTime;

    public ResponseEntity<Map<String, Object>> daftarAkun(UserPojo param) {
        Map<String, Object> response = new HashMap<>();

        try {
            UserModel findUser = userMapper.findUser(param.getEmail(), param.getPassword());
            if (findUser == null) {
                UUID uuid = UUID.randomUUID();
                String uuidString = uuid.toString();

                param.setIdUser(uuidString);

                userMapper.daftarAkun(param);

                response.put("statusCode", HttpStatus.OK.value());
                response.put("message", "daftar sukses.");
            } else {
                response.put("statusCode", HttpStatus.ALREADY_REPORTED.value());
                response.put("message", "akun telah terdaftar.");
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(response);
        }
    }

    public ResponseEntity<Map<String, Object>> loginAkun(String email, String password){
        Map<String, Object> response = new HashMap<>();
        try {
            if(email.isEmpty() || password.isEmpty()){
                response.put("statusCode", HttpStatus.BAD_REQUEST.value());
                response.put("message", "email dan password harus diisi.");
            }else {
                boolean statusEmail = EmailValidationUtil.validate(email);
                if(!statusEmail){
                    response.put("statusCode", HttpStatus.BAD_REQUEST.value());
                    response.put("message", "email tidak sesuai format.");
                }else {
                    LoginPojo param = new LoginPojo();
                    param.setEmail(email);
                    param.setPassword(password);

                    UserModel dataUser = userMapper.findUser(param.getEmail(), param.getPassword());
                    if (dataUser == null) {
                        response.put("statusCode", HttpStatus.NOT_FOUND.value());
                        response.put("message", "pengguna belum terdaftar");
                    } else {
                        UUID uuid = UUID.randomUUID();

                        String token = jwtUtil.generateToken(param);

                        HistoryLoginPojo historyLoginPojo = new HistoryLoginPojo();
                        historyLoginPojo.setIdHistoryLogin(uuid.toString());
                        historyLoginPojo.setIdUser(dataUser.getIdUser());
                        historyLoginPojo.setToken(token);
                        historyLoginPojo.setExpiredTime(expiredTime);

                        userMapper.addNewToken(historyLoginPojo);

                        response.put("statusCode", HttpStatus.OK.value());
                        response.put("message", "login berhasil.");
                        response.put("token", token);
                    }
                }
            }
            return ResponseEntity.ok(response);
        }catch (Exception e){
            e.printStackTrace();
            response.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(response);
        }
    }
}
