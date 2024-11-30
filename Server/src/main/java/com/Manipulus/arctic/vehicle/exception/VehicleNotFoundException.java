package com.Manipulus.arctic.vehicle.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class VehicleNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public VehicleNotFoundException(String message) {
        super(message);
    }
}
