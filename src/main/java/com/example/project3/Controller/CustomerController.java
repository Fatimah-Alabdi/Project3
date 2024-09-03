package com.example.project3.Controller;

import com.example.project3.Api.ApiResponse;
import com.example.project3.DTO.CustomerDTO;
import com.example.project3.Model.User;
import com.example.project3.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping("/get-all-customer")
    public ResponseEntity getAllCustomer(){
        return ResponseEntity.status(200).body(customerService.getAllCustomers());
    }
    @PostMapping("/register")
    public ResponseEntity registerCustomer( @Valid @RequestBody CustomerDTO customerDTO) {
        customerService.registerCustomer( customerDTO);
        return ResponseEntity.status(200).body(new ApiResponse( "Customer registered successfully"));
    }
    @PutMapping("/update")
    public ResponseEntity updateCustomer(@AuthenticationPrincipal User user, @Valid @RequestBody CustomerDTO customerDTO) {
        customerService.updateCustomer(user.getId(), customerDTO);
        return ResponseEntity.status(200).body(new ApiResponse( "Customer updated successfully"));

    }
    @DeleteMapping("/delete")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal User user) {
        customerService.deleteCustomer(user.getId());
        return ResponseEntity.status(200).body(new ApiResponse( "Customer deleted successfully"));
    }
}
