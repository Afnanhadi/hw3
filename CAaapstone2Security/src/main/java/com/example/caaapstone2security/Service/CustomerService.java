package com.example.caaapstone2security.Service;

import com.example.caaapstone2security.Api.ApiException;
import com.example.caaapstone2security.DTO.CustomerDTO;
import com.example.caaapstone2security.Model.Customer;
import com.example.caaapstone2security.Model.User;
import com.example.caaapstone2security.Repository.AuthRepostory;
import com.example.caaapstone2security.Repository.CustomerRepostory;
import com.example.caaapstone2security.Repository.ProductRepostory;
import com.example.caaapstone2security.Repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepostory customerRepostory;
    private final AuthRepostory authRepostory;

    public List<Customer> getAll(){
        return customerRepostory.findAll();
    }
    public void addCustomer(CustomerDTO customerDTO, Integer user_id){
        User user=authRepostory.findUserById(user_id);
        if(user==null){
            throw  new ApiException("user nof found");
        }
        Customer customer1=customerRepostory.findUserById(user_id);
        if (user.getRole().equals("STORE"))
            throw new ApiException ("the customer can`t add a store  ");
        if (customer1 !=null){
            throw new ApiException ("not found");
        }
        Customer customer2=new Customer();
        customer2.setEmail(customerDTO.getEmail());
        customer2.setSalary(customerDTO.getSalary());
        customer2.setPoints(customerDTO.getPoints());
        customer2.setUser(user);

      customerRepostory.save(customer2);
    }
    public List<Customer> getMyCustomer(Integer user_id) {
        User user=authRepostory.findUserById(user_id);
        return customerRepostory.findAllByUser(user);
    }
    public void updateUser(CustomerDTO customerDTO, Integer customer_id,Integer user_id){
        Customer customer1=customerRepostory.findUserById(customer_id);
        if (customer1==null){
            throw new ApiException("id not found");
        }
        User user=authRepostory.findUserById(user_id);
        if (customer1.getUser().getId() !=user_id)
        {
            throw new ApiException("the customer not found");
        }
        customer1.setSalary(customerDTO.getSalary());
        customer1.setEmail(customerDTO.getEmail());
        customer1.setPoints(customerDTO.getPoints());
        customerRepostory.save(customer1);

    }
    public void deleteUser( Integer customer_id,Integer user_id){
        Customer customer=customerRepostory.findUserById(customer_id);

        if (customer==null){
            throw new ApiException("nof found");
        }
        User user=authRepostory.findUserById(user_id);
        if (customer.getUser().getId() !=user_id)
        {
            throw new ApiException("the customer not found");
        }
        customerRepostory.delete(customer);
    }
   /* public List<Customer> nameUser(String name){
        List<Customer> customers=customerRepostory.findUserByName(name);
        if (customers.isEmpty()){
            throw new ApiException("is Empty");
        }
        return customers;
    }*/


  /*  public boolean checkPasswordEmail(String password,String email){
       boolean users= customerRepostory.existsByEmailAndPassword(password,email);
        if (users==false){
            throw new ApiException("is Empty");
        }
          return true;
    }*/




    public void buyByPoints(Double ProductsPrice,Integer customer_id,Integer user_id){

        Customer customer=customerRepostory.findUserById(customer_id);
        if (customer==null){
            throw new ApiException("not found");
        }
        User user=authRepostory.findUserById(user_id);
        if (customer.getUser().getId() !=user_id)
        {
            throw new ApiException("the customer not found");
        }
        if (customer.getPoints()<ProductsPrice){
            throw  new ApiException("The price of the product is larger than points");
        }


    }

}
