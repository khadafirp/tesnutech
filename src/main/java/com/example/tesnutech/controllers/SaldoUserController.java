package com.example.tesnutech.controllers;

import com.example.tesnutech.services.SaldoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/saldo")
public class SaldoUserController {

    @Autowired
    SaldoUserService saldoUserService;

    @GetMapping("/cek-saldo")
    public ResponseEntity<Map<String, Object>> cekSaldo(
            @RequestHeader(value = "Authorization", required = true, defaultValue = "") String authorization
    ){
        return saldoUserService.cekSaldo(authorization);
    }

}
