package com.example.neilassignment2af.controllers.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record NewPropertyForTenantDTO(@NotNull(message = "Property Id cannot be null")
                                      @Min(value = 1, message = ("The property Id cannot be below 1.")) Integer newPropertyId) {
}
