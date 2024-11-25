package com.assignment.Week2Assignment.dtos;

import com.assignment.Week2Assignment.annotations.Password;
import com.assignment.Week2Assignment.annotations.PrimeNumber;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.time.LocalDateTime;

@Data
public class DepartmentDTO {
    private Long id;

    @NotBlank(message = "Department Title cannot be empty")
    @Size(min = 2,max = 5,message = "Department Title should be greater than 1 & less than 101 ")
    private String title;

    @NotNull(message = "isActive cannot be Null")
    @AssertTrue(message = "isActive should always be true.")
    @JsonProperty("isActive")
    private Boolean isActive;

    @PastOrPresent(message = "createdAt should be a Past or Present date")
    @JsonProperty("createdAt")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonProperty("updatedAt")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    @CreditCardNumber(message = "Not a valid Credit card number.")
    private String creditCardNumber;

    @Password(message = "Password cannot be empty and must contain 1 Upppercase,1 Lowercase, 1 special character and must be atleast 10 characters in size.")
    private String password;

    @PrimeNumber(message = "Number should be a prime Number.")
    private Integer primeNumber;
}
