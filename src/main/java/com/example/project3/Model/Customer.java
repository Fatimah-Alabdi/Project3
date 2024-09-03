package com.example.project3.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer  {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Phone number cannot be null")
    @Pattern(regexp = "^05\\d{8}$", message = "Phone number must start with '05' and be 10 digits long")
    private String phoneNumber;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Account> accounts;
}
