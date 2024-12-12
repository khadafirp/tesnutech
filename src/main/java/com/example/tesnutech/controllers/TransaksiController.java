package com.example.tesnutech.controllers;

import com.example.tesnutech.pojos.HistoryTransaksiPojo;
import com.example.tesnutech.services.TransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/transaksi")
public class TransaksiController {

    @Autowired
    TransaksiService transaksiService;

    @PostMapping("/transaction")
    public ResponseEntity<Map<String, Object>> pembayaran(
            @RequestHeader(value = "Authorization") String authorization,
            @RequestBody HistoryTransaksiPojo param
    ){
        return transaksiService.pembayaran(authorization, param);
    }

}
