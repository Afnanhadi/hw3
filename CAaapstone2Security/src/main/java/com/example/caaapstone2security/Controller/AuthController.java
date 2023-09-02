package com.example.caaapstone2security.Controller;

import com.example.caaapstone2security.Api.ApiResponse;
import com.example.caaapstone2security.Model.User;
import com.example.caaapstone2security.Service.AuthService;
import com.example.caaapstone2security.Service.CustomerService;
import com.example.caaapstone2security.Service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    public final AuthService authService;

    @PostMapping("/customer/register")
    public ResponseEntity registercustomer(@RequestBody @Valid User user){
        authService.registerCustomer(user);
        return ResponseEntity.status(200).body(new ApiResponse("user register"));
    }

    @PostMapping("/store/register")
    public ResponseEntity registerStore(@RequestBody @Valid User user){
        authService.registerStore(user);
        return ResponseEntity.status(200).body(new ApiResponse("user register"));
    }
}

