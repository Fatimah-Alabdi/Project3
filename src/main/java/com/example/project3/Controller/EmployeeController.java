package com.example.project3.Controller;

import com.example.project3.Api.ApiResponse;
import com.example.project3.DTO.CustomerDTO;
import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.User;
import com.example.project3.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/get-all-employee")
    public ResponseEntity getAllEmployee(){
        return ResponseEntity.status(200).body(employeeService.getAllEmployees());
    }
    @PostMapping("/register")
    public ResponseEntity registerEmployee( @Valid @RequestBody EmployeeDTO employeeDTO) {
        employeeService.registerEmployee(employeeDTO);
        return ResponseEntity.status(200).body(new ApiResponse( "employee registered successfully"));
    }
    @PutMapping("/update")
    public ResponseEntity updateEmployee(@AuthenticationPrincipal User user, @Valid @RequestBody EmployeeDTO employeeDTO) {
        employeeService.updateEmployee(user.getId(), employeeDTO);
        return ResponseEntity.status(200).body(new ApiResponse( "employee updated successfully"));
    }
    @DeleteMapping("/delete")
    public  ResponseEntity deleteEmployee(@AuthenticationPrincipal User user) {
        employeeService.deleteEmployee(user.getId());
        return ResponseEntity.status(200).body(new ApiResponse( "employee deleted successfully"));
    }
}
