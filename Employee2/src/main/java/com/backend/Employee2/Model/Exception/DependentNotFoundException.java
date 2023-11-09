package com.backend.Employee2.Model.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DependentNotFoundException extends  RuntimeException{
    public DependentNotFoundException(Long id) {
       super(MessageFormat.format("Could not find dependent with id: {0}", id));
    }
}
