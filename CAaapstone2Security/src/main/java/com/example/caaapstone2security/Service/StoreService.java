package com.example.caaapstone2security.Service;

import com.example.caaapstone2security.Api.ApiException;
import com.example.caaapstone2security.DTO.StoreDTO;
import com.example.caaapstone2security.Model.Customer;
import com.example.caaapstone2security.Model.Store;
import com.example.caaapstone2security.Model.User;
import com.example.caaapstone2security.Repository.AuthRepostory;
import com.example.caaapstone2security.Repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final AuthRepostory authRepostory;


    public List<Store> getAllStore(){
        return storeRepository.findAll();
    }
    public List<Store> getMyStore(Integer user_id) {
        User user=authRepostory.findUserById(user_id);
        return storeRepository.findAllByUser(user);
    }

    public void addStore(StoreDTO storeDTO, Integer store_id){
        User user=authRepostory.findUserById(store_id);
        Store store=storeRepository.findStoreById(store_id);
        if (user.getRole().equals("CUSTOMER"))
            throw new ApiException ("the store can`t add a customer  ");
        if (store !=null){
            throw new ApiException ("not found");
        }
        if (user==null){
            throw new ApiException("user not found");
        }
        Store store1=new Store();
        store1.setNameStore(storeDTO.getNameStore());
        store1.setState(storeDTO.getState());
        store1.setAddress(storeDTO.getAddress());
        store1.setUser(user);
        storeRepository.save(store1);
    }
    public void UpdateStore(StoreDTO storeDTO, Integer store_id,Integer user_id){
        Store store1= storeRepository.findStoreById(store_id);
        if (store1==null){
            throw new ApiException("id not found");
        }
        User user=authRepostory.findUserById(user_id);
        if (store1.getUser().getId() !=user_id)
        {
            throw new ApiException("the todo not found");
        }

        store1.setAddress(storeDTO.getAddress());
        store1.setNameStore(storeDTO.getNameStore());
        store1.setState(storeDTO.getState());

        storeRepository.save(store1);
    }

    public void changeStatus(Integer Id){
        Store store1= storeRepository.findStoreById(Id);
        if(store1==null){
            throw new ApiException("not found id");
        }
        if (store1.getState().equals("Available")){
            store1.setState("Locked");
        }
        else if (store1.getState().equals("Locked")){
            store1.setState("Available");
        }
    }
    public void deleteStore(Integer user_id,Integer store_id){
        Store store=storeRepository.findStoreById(store_id);
        if (store==null){
            throw new ApiException("the todo not found");
        }
        if (store.getUser().getId() !=user_id){
            throw new ApiException("the todo not found");

        }
        storeRepository.delete(store);
    }


}