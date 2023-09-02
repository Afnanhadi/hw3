package com.example.caaapstone2security.Service;

import com.example.caaapstone2security.Model.User;
import com.example.caaapstone2security.Repository.AuthRepostory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepostory authRepostory;

    public void registerStore(User user) {
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        user.setRole("STORE");
        authRepostory.save(user);
    }
    public void registerCustomer(User user) {
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        user.setRole("CUSTOMER");
        authRepostory.save(user);
    }

}