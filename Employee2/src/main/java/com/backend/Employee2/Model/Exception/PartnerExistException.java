package com.backend.Employee2.Model.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PartnerExistException extends RuntimeException{
    public PartnerExistException(Long id) {
        super(MessageFormat.format("Could not have both spouse and domestics partner for employee", id));

    }
}
