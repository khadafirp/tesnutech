package com.example.tesnutech.mapper;

import com.example.tesnutech.model.HistoryLoginModel;
import com.example.tesnutech.model.UserModel;
import com.example.tesnutech.pojos.HistoryLoginPojo;
import com.example.tesnutech.pojos.UserPojo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

@Mapper
public interface UserMapper {

    UserModel findUser(@Param("email") String email, @Param("password") String password);

    void daftarAkun(UserPojo param);

    HistoryLoginModel findUserToken(@Param("token") String token);

    void addNewToken(HistoryLoginPojo param);

}
