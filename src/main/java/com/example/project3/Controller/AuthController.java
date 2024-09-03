package com.example.project3.Controller;

import com.example.project3.Model.User;
import com.example.project3.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/get")
    public ResponseEntity getAllUser(){
        return ResponseEntity.status(200).body(authService.findAll());
    }

//   @PutMapping("/update/{id}")
//  public ResponseEntity updateUser(@AuthenticationPrincipal User user, @Valid @RequestBody User user) {
//       authService.update(id,user);
//       return ResponseEntity.status(200).body("user updated successfully");}
////    @DeleteMapping("/delete/{id}")
////    public ResponseEntity deleteUser(@PathVariable Integer id) {
////        authService.delete(id);
////        return ResponseEntity.status(200).body("user deleted successfully");
////    }
}
