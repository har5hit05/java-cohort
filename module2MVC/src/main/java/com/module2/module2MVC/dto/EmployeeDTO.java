package com.module2.module2MVC.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.module2.module2MVC.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "Name of the employee cannot be blank")
    @Size(min = 3, max = 10, message = "Number of characters in name should in the range [3, 10]")
    private String name;

    @NotBlank(message = "Email of the employee cannot be blank")
    @Email(message = "Email should be a valid email")
    private String email;

    @NotNull(message = "Age of the employee cannot be blank")
    @Max(value = 80, message = "Age of employee cannot be greater than 80")
    @Min(value = 18, message = "Age of employee cannot be lesser than 18")
    private Integer age;

    @NotBlank(message = "Role of employee cannot be blank")
//    @Pattern(regexp = "^[ADMIN|USER]$]", message = "Role of employee can either be ADMIN or USER")
    @EmployeeRoleValidation
    private String role;

    @NotNull(message = "Salary of employee cannot be null")
    @Positive(message = "Salary of employee cannot be null")
    @Digits(integer = 6, fraction = 2, message = "The salary of employee can be in the form XXXXXX.YY")
    @DecimalMax(value = "100000.99")
    @DecimalMin(value = "100.50")
    private Double salary;

    @PastOrPresent(message = "Date of Joining filed in employee cannot be of future")
    private LocalDate dateOfJoining;

    @AssertTrue(message = "Employee should be active")
    @JsonProperty("isActive")
    private Boolean isActive;
}
