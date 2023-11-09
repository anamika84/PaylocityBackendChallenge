package com.backend.Employee2.Model.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SalaryException extends RuntimeException{
    public SalaryException(Double Salary) {
        super(MessageFormat.format("Salary cant be in negative after deducting all taxes: {0}", Salary));

    }
}
