package com.example.neilassignment2af.controllers.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record NewPropertyRentDTO(@NotNull(message = "Property cost cannot be null")
                              @Min(value = 0, message = ("The cost cannot be below 0."))
                              Integer newPropertyCost) {
}
