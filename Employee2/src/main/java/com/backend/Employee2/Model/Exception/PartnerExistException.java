package com.backend.Employee2.Model.Exception;

import java.text.MessageFormat;

public class PartnerExistException extends RuntimeException{
    public PartnerExistException(Long id) {
        super(MessageFormat.format("Could not have both spouse and domestics partner for employee", id));

    }
}
