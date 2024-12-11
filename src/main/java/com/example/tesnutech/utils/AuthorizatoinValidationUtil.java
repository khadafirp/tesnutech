package com.example.tesnutech.utils;

import com.example.tesnutech.mapper.SaldoUserMapper;
import com.example.tesnutech.mapper.UserMapper;
import com.example.tesnutech.model.SaldoUserModel;
import com.example.tesnutech.model.UserModel;
import com.example.tesnutech.pojos.UserPojo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Map;

@Service
public class AuthorizatoinValidationUtil {

    @Autowired
    UserMapper userMapper;

    @Autowired
    SaldoUserMapper saldoUserMapper;

    @Autowired
    JwtUtil jwtUtil;

    ObjectMapper mapper = new ObjectMapper();

    public int statusCodeAuth(String authorization) throws JsonProcessingException {
        int statusCode = 0;
        if(authorization.isEmpty()){
            statusCode = HttpStatus.UNAUTHORIZED.value();
        }else {
            String[] split = authorization.split(" ");

            String tokenExtracted = jwtUtil.extractData(split[1]);
            Map<String, Object> data = mapper.readValue(tokenExtracted, new TypeReference<Map<String, Object>>() {
                @Override
                public Type getType() {
                    return super.getType();
                }
            });

            UserModel dataUser = userMapper.findUser(data.get("email").toString(), data.get("password").toString());

            UserPojo param = new UserPojo();
            param.setEmail(dataUser.getEmail().isEmpty() ? null : dataUser.getEmail());
            param.setPassword(dataUser.getPassword().isEmpty() ? null : dataUser.getPassword());

            boolean isValid = jwtUtil.isTokenValid(split[1], param);
            boolean isExpired = jwtUtil.isTokenExpired(split[1]);

            if(!isValid){
                statusCode = HttpStatus.UNAUTHORIZED.value();
            }

            if (!isExpired){
                statusCode = HttpStatus.UNAUTHORIZED.value();
            }

            statusCode = HttpStatus.OK.value();
        }
        return statusCode;
    }

    public Map<String, Object> dataJsonAuth(String authorization) throws JsonProcessingException {
        String[] split = authorization.split(" ");

        String tokenExtracted = jwtUtil.extractData(split[1]);
        return mapper.readValue(tokenExtracted, new TypeReference<Map<String, Object>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
    }
}
