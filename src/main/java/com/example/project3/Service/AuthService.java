package com.example.project3.Service;

import com.example.project3.Api.ApiException;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    public List<User> findAll() {
        return authRepository.findAll();
    }

//    public void update(Integer id,User user) {
//        User findUser = authRepository.findUserById(id);
//        if(findUser == null){
//            throw new ApiException("user not found");
//        }
//        findUser.setPassword(user.getPassword());
//        findUser.setUsername(user.getUsername());
//        authRepository.save(findUser);
//
//    }
//    public void delete(Integer id) {
//        User findUser = authRepository.findUserById(id);
//        if(findUser == null){
//            throw new ApiException("user not found");
//        }
//        authRepository.delete(findUser);
//    }
}
