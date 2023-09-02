package com.example.caaapstone2security;

import com.example.caaapstone2security.Model.Store;
import com.example.caaapstone2security.Model.User;
import com.example.caaapstone2security.Repository.AuthRepostory;
import com.example.caaapstone2security.Repository.StoreRepository;
import com.example.caaapstone2security.Service.StoreService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StoreServiceTest {

    @InjectMocks
    StoreService storeService;

    @Mock
    StoreRepository storeRepository;
    @Mock
    AuthRepostory authRepostory;

    User user;
    Store store1,store2;
    List<Store> stores;

    @BeforeEach
    void setUp() {
        user=new User(1,"afnan","asdf1234","CUSTOMER",null,null);
        store1=new Store(null,"afnan","london","Available",null,null);
        store2=new Store(null,"amjadcoffee","london","Available",null,null);
        stores=new ArrayList<>();
        stores.add(store1);
        stores.add(store2);
    }

    @Test
    public void getAllStoreTest(){
        when(storeRepository.findAll()).thenReturn(stores);
        List<Store> StoreList=storeService.getAllStore();
        Assertions.assertEquals(2,StoreList.size());
        Mockito.verify(storeRepository,times(1)).findAll();

    }
    @Test
    public void getMyStore(){
        when(authRepostory.findUserById(user.getId())).thenReturn(user);
        when(storeRepository.findAllByUser(user)).thenReturn(stores);
        List<Store> storeList=storeService.getMyStore(user.getId());

        verify(storeRepository,times(1)).findAllByUser(user);
        verify(authRepostory,times(1)).findUserById(user.getId());
    }
}
