package com.example.caaapstone2security.Service;

import com.example.caaapstone2security.Api.ApiException;
import com.example.caaapstone2security.Model.User;
import com.example.caaapstone2security.Repository.AuthRepostory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final AuthRepostory authRepostory;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=authRepostory.findUserByUsername(username);
        if (user==null){
            throw  new ApiException("j");
        }
        return user;
    }
}