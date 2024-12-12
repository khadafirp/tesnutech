package com.example.tesnutech.mapper;

import com.example.tesnutech.pojos.HistoryTransaksiPojo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HistoryTransaksiMapper {

    void addNewTransaksi(HistoryTransaksiPojo param);

}
