package com.example.neilassignment2af.controllers.dtos;

import jakarta.validation.constraints.*;

public record NewTenantDTO(
        @NotEmpty(message = "Tenant name cannot be empty")
        @NotNull(message = "Tenant name cannot be null")
        @NotBlank(message = "Tenant name cannot be blank")
        String tenantName,
        @NotEmpty(message = "Tenant email cannot be empty")
        @NotNull(message = "Tenant email cannot be null")
        @NotBlank(message = "Tenant email cannot be blank")
        @Email(message= "Not a valid email address")
        String tenantEmail,
        @NotEmpty(message = "Tenant phone number cannot be empty")
        @NotNull(message = "Tenant phone number be null")
        @NotBlank(message = "Tenant phone number be blank")
        String tenantPhoneNumber,

        @NotNull(message = "Property Id cannot be null")
        @Min(value = 1, message = ("The property Id cannot be below 1."))
        Integer propertyId) {
}
