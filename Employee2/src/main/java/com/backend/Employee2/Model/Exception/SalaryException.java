package com.backend.Employee2.Model.Exception;

import java.text.MessageFormat;

public class SalaryException extends RuntimeException{
    public SalaryException(Double Salary) {
        super(MessageFormat.format("Salary cant be in negative after deducting all taxes: {0}", Salary));

    }
}
