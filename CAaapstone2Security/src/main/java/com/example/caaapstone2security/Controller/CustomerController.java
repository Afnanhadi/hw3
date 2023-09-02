package com.example.caaapstone2security.Controller;

import com.example.caaapstone2security.Api.ApiResponse;
import com.example.caaapstone2security.DTO.CustomerDTO;
import com.example.caaapstone2security.Model.Customer;
import com.example.caaapstone2security.Model.User;
import com.example.caaapstone2security.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;


    @GetMapping("/get")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(customerService.getAll());
    }


    @PostMapping("/add")

    public ResponseEntity addUser(@RequestBody @Valid CustomerDTO customerDTO , @AuthenticationPrincipal User user){
        customerService.addCustomer(customerDTO ,user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("add user"));
    }

    @GetMapping("/get-my-customer")
    public ResponseEntity getMyCustomer(@AuthenticationPrincipal User user ){
        return ResponseEntity.status(200).body(customerService.getMyCustomer(user.getId()));
    }
    @PutMapping("/update/{customer_id}")
    public ResponseEntity updateUser(@RequestBody @Valid CustomerDTO customerDTO, @PathVariable  Integer customer_id,@AuthenticationPrincipal User user){
        customerService.updateUser(customerDTO,customer_id,user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Update user"));
    }
    @DeleteMapping("/delete/{Id}")
    public ResponseEntity deleteUser(@PathVariable Integer Id, @AuthenticationPrincipal User user){
        customerService.deleteUser(Id,user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("delete user"));
    }
   /* @GetMapping("/Search/{name}")
    public ResponseEntity nameUser(@PathVariable String name){
        return ResponseEntity.status(200).body( customerService.nameUser(name));
    }*/

    /* @PostMapping("/check")
    public ResponseEntity LoginPasswordEmail(String password, String email,@RequestBody @Valid Customer customer){

        customerService.checkPasswordEmail(,customer.getEmail());

     return ResponseEntity.status(200).body(new ApiResponse("Login"));
    }*/

    @GetMapping("/checkPoints/{ProductsPrice}/{customer_id}")
    public ResponseEntity checkPoints(@PathVariable Double ProductsPrice,@PathVariable Integer customer_id,@AuthenticationPrincipal User user){
        customerService.buyByPoints(ProductsPrice,customer_id,user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("The product was successfully"));

    }
}
