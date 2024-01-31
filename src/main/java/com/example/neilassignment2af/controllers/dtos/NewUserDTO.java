package com.example.neilassignment2af.controllers.dtos;

import jakarta.validation.constraints.*;

public record NewUserDTO(@NotEmpty(message = "User email cannot be empty")
                         @NotNull(message = "User email cannot be null")
                         @NotBlank(message = "User email cannot be blank")
                         @Email(message= "Not a valid email address")
                         String userEmail,

                         @NotEmpty(message = "User password cannot be empty")
                         @NotNull(message = "User password cannot be null")
                         @NotBlank(message = "User password cannot be blank")
                         @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$", message = "Password must be at least 8 characters long and include a minimum of 1 uppercase letter, 1 lowercase letter and 1 digit.")
                         String userPassword,

                         @NotNull(message = "User locked cannot be null")
                         Boolean userLocked,

                         @NotEmpty(message = "User role cannot be empty")
                         @NotNull(message = "User role cannot be null")
                         @NotBlank(message = "User role cannot be blank")
                         String userRole,
                         @NotEmpty(message = "User phone number cannot be empty")
                         @NotNull(message = "User phone number cannot be null")
                         @NotBlank(message = "User phone number cannot be blank")
                         String userPhoneNumber,

                         @NotEmpty(message = "User ppsn cannot be empty")
                         @NotNull(message = "User ppsn cannot be null")
                         @NotBlank(message = "User ppsn cannot be blank")
                         String userPPSN) {
}
