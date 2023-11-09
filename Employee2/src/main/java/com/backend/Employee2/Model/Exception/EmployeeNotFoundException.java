package com.backend.Employee2.Model.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException{
    public  EmployeeNotFoundException(Long id) {
        super(MessageFormat.format("Could not find employee with id: {0}", id));

    }
}
