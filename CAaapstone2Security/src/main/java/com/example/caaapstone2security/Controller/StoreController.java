package com.example.caaapstone2security.Controller;

import com.example.caaapstone2security.Api.ApiResponse;
import com.example.caaapstone2security.DTO.StoreDTO;
import com.example.caaapstone2security.Model.Store;
import com.example.caaapstone2security.Model.User;
import com.example.caaapstone2security.Service.CustomerService;
import com.example.caaapstone2security.Service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/store")
public class StoreController {
    private final StoreService storeService;
    private final CustomerService customerService;



    @GetMapping("/get")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(storeService.getAllStore());
    }
    @GetMapping("/get-my-store")
    public ResponseEntity getMystore(@AuthenticationPrincipal User user ){
        return ResponseEntity.status(200).body(storeService.getMyStore(user.getId()));
    }
    @PostMapping("/add")
    public ResponseEntity addStore(@RequestBody @Valid StoreDTO storeDTO, @AuthenticationPrincipal User user){
        storeService.addStore(storeDTO,user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("add store"));
    }
    @PutMapping("/update/{store_id}")
    public ResponseEntity UpdateStore(@RequestBody @Valid StoreDTO storeDTO,@PathVariable Integer store_id,@AuthenticationPrincipal User user){
        storeService.UpdateStore(storeDTO,store_id,user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Update Store"));
    }
    @DeleteMapping("/delete/{store_id}")
    public ResponseEntity deleteTodo( @PathVariable Integer store_id,@AuthenticationPrincipal User user) {
        storeService.deleteStore(store_id,user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Delete Store"));
    }
    @PutMapping("/change/{Id}")
    public ResponseEntity changeStatus(@PathVariable Integer Id){
        storeService.changeStatus(Id);
        return ResponseEntity.status(200).body(new ApiResponse("change Status"));
    }



}
