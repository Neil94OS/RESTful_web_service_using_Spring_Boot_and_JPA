package com.example.neilassignment2af.controllers.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewPropertyDTO(
        @NotEmpty(message = "Property address cannot be empty")
        @NotNull(message = "Property address cannot be null")
        @NotBlank(message = "Property address cannot be blank")
        String propertyAddress,
        @NotEmpty(message = "Property eircode cannot be empty")
        @NotNull(message = "Property eircode cannot be null")
        @NotBlank(message = "Property eircode cannot be blank")
        String propertyEircode,
        @Min(value = 1, message = ("The capacity cannot be below 1."))
        Integer propertyCapacity,

        @NotNull(message = "Property cost cannot be null")
        @Min(value = 0, message = ("The cost cannot be below 0."))
        Integer propertyCost) {
}
