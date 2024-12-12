package com.example.tesnutech.mapper;

import com.example.tesnutech.model.PpobMerchantModel;
import com.example.tesnutech.pojos.PpobMerchantPojo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PpobMerchantMapper {

    PpobMerchantModel getMerchant(PpobMerchantPojo param);

}
