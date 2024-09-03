package com.example.project3.Service;

import com.example.project3.Api.ApiException;
import com.example.project3.DTO.CustomerDTO;
import com.example.project3.Model.Customer;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;
    private final AuthRepository authRepository;

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public void registerCustomer(CustomerDTO customerDTO) {
      User user = new User();
      Customer customer = new Customer();

        user.setUsername(customerDTO.getUsername());
        String hash = new BCryptPasswordEncoder().encode(customerDTO.getPassword());
        user.setPassword(hash);
        user.setName(customerDTO.getName());
        user.setEmail(customerDTO.getEmail());
        user.setRole("CUSTOMER");
        authRepository.save(user);


        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setUser(user);

        customerRepository.save(customer);

    }



    public void updateCustomer(Integer customer_id, CustomerDTO customerDTO) {

        Customer customer = customerRepository.findCustomerById(customer_id);
        User user = authRepository.findUserById(customer_id);


        user.setUsername(customerDTO.getUsername());
        if (customerDTO.getPassword() != null && !customerDTO.getPassword().isEmpty()) {
            user.setPassword(new BCryptPasswordEncoder().encode(customerDTO.getPassword()));
        }
        user.setName(customerDTO.getName());
        user.setEmail(customerDTO.getEmail());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setUser(user);
        authRepository.save(user);

        customerRepository.save(customer);
    }

    public void deleteCustomer(Integer customer_id) {
        Customer customer = customerRepository.findCustomerById(customer_id);
        customerRepository.delete(customer);
    }


}
