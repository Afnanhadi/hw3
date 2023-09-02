package com.example.caaapstone2security.Repository;

import com.example.caaapstone2security.Model.Store;
import com.example.caaapstone2security.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store,Integer> {
    Store findStoreById (Integer Id);
    List<Store> findAllByUser (User user);

}