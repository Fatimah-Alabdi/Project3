package com.example.project3.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Account number cannot be null")
    @Pattern(regexp = "^\\d{4}-\\d{4}-\\d{4}-\\d{4}$", message = "Account number must follow the format XXXX-XXXX-XXXX-XXXX")
    @Column(columnDefinition = "varchar(25) not null")
    private String accountNumber;

    @DecimalMin(value = "0.0", inclusive = true, message = "Balance must be a non-negative decimal number")
    @NotNull(message = "Balance cannot be null")
    @Column(columnDefinition = " Double not null")
    private Double balance;

    @Column(nullable = false)
    private Boolean isActive = false;

    @ManyToOne
    //@JoinColumn(name = "customer_id",referencedColumnName = "id")
    @JsonIgnore
    private Customer customer;

    @ManyToOne
    //@JoinColumn(name = "customer_id",referencedColumnName = "id")
    @JsonIgnore
    private User user;
}
