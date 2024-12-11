package com.example.tesnutech.mapper;

import com.example.tesnutech.model.SaldoUserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SaldoUserMapper {

    SaldoUserModel findSaldoUser(@Param("idUser") String idUser);

}
