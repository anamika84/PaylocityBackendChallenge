package com.backend.Employee2.Model.Exception;

import java.text.MessageFormat;

public class DependentNotFoundException extends  RuntimeException{
    public DependentNotFoundException(Long id) {
        super(MessageFormat.format("Could not find dependent with id: {0}", id));
    }
}
