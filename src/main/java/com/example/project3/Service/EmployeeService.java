package com.example.project3.Service;

import com.example.project3.DTO.CustomerDTO;
import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.Customer;
import com.example.project3.Model.Employee;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final AuthRepository authRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();

    }
    public void registerEmployee(EmployeeDTO employeeDTO) {
        User user = new User();
        Employee employee = new Employee();



        user.setUsername(employeeDTO.getUsername());


        String hash = new BCryptPasswordEncoder().encode(employeeDTO.getPassword());
        user.setPassword(hash);

        user.setName(employeeDTO.getName());
        user.setEmail(employeeDTO.getEmail());

        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());


        user.setRole("EMPLOYEE");
        employee.setUser(user);

        authRepository.save(user);

        employeeRepository.save(employee);
    }
    public void updateEmployee(Integer employee_id, EmployeeDTO employeeDTO) {

        Employee employee = employeeRepository.findEmployeeById(employee_id);
        User user = authRepository.findUserById(employee_id);

        user.setUsername(employeeDTO.getUsername());


        if (employeeDTO.getPassword() != null && !employeeDTO.getPassword().isEmpty()) {
            user.setPassword(new BCryptPasswordEncoder().encode(employeeDTO.getPassword()));
        }

        user.setName(employeeDTO.getName());
        user.setEmail(employeeDTO.getEmail());
        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());


        // employee.setRole("EMPLOYEE");
        employee.setUser(user);

        authRepository.save(user);


        employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer employee_id) {
        Employee employee = employeeRepository.findEmployeeById(employee_id);



        employeeRepository.delete(employee);
    }
}
