package com.backend.Employee2.Model.Exception;

import java.text.MessageFormat;

public class EmployeeNotFoundException extends RuntimeException{
    public  EmployeeNotFoundException(Long id) {
        super(MessageFormat.format("Could not find employee with id: {0}", id));

    }
}
