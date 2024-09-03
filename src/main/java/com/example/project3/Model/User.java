package com.example.project3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Username cannot be null")
    @Size(min = 4, max = 10, message = "Username must be between 4 and 10 characters")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String username;

    @NotBlank(message = "Password cannot be null")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Column(columnDefinition = "varchar(300) not null")
    private String password;

    @NotBlank(message = "Name cannot be null")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotBlank(message = "Email cannot be null")
    @Email(message = "Must be a valid email format")
    @Column(columnDefinition = "varchar(100) not null unique")
    private String email;

    @NotBlank(message = "Role cannot be null")
    @Pattern(regexp = "CUSTOMER|EMPLOYEE|ADMIN", message = "Role must be either CUSTOMER, EMPLOYEE, or ADMIN")
    @Column(columnDefinition = "varchar(9) not null")
    private String role;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Customer customer;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Employee employee;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Account> accounts;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
