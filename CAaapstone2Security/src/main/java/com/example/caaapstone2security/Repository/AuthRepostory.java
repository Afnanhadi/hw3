package com.example.caaapstone2security.Repository;

import com.example.caaapstone2security.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepostory extends JpaRepository<User,Integer> {
    User findUserByUsername(String username);
    User findUserById (Integer id);

}
