package com.example.project3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Position cannot be null")
    private String position;

    @DecimalMin(value = "0.0", inclusive = true, message = "Salary must be a non-negative decimal number")
    @NotNull(message = "Salary cannot be null")
    private Double salary;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

}
