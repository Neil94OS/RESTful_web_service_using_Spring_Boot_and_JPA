package com.example.neilassignment2af.controllers.handlers;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record ApiErrorResponse(String message, @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyy hh:mm") LocalDateTime localDateTime) {
}
