package com.example.tesnutech.controllers;

import com.example.tesnutech.pojos.UserPojo;
import com.example.tesnutech.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/daftar-akun")
    public ResponseEntity<Map<String, Object>> daftarAkun(
            @RequestBody UserPojo param
    ){
        return userService.daftarAkun(param);
    }

    @GetMapping("/masuk-akun")
    public ResponseEntity<Map<String, Object>> masukAkun(
            @RequestParam(value = "email", defaultValue = "") String email,
            @RequestParam(value = "password", defaultValue = "") String password
    ){
        return userService.loginAkun(email, password);
    }

}
