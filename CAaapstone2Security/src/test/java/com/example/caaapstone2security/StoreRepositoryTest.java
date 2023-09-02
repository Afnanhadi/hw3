package com.example.caaapstone2security;

import com.example.caaapstone2security.Model.Store;
import com.example.caaapstone2security.Model.User;
import com.example.caaapstone2security.Repository.StoreRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class StoreRepositoryTest {

    @Autowired
    StoreRepository storeRepository;

    User user;
    Store store1;
    Store store2;


    @BeforeEach
    void setUp() {
        user=new User(1,"afnan","asdf1234","CUSTOMER",null,null);
        store1=new Store(1,"afnan","london","Available",null,null);
        store2=new Store(1,"amjadcoffee","london","Available",null,null);

    }

    @Test
    public void findAllByUser(){
        storeRepository.save(store1);
        storeRepository.save(store2);
        List<Store> storeList= storeRepository.findAllByUser(user);
        Assertions.assertThat(storeList.get(0).getUser().getId()).isEqualTo(user.getId());
    }

    @Test
    public void findStoreById(){
        storeRepository.save(store1);
        Store store= storeRepository.findStoreById(store1.getId());
        Assertions.assertThat(store).isEqualTo(store1);
    }

}
