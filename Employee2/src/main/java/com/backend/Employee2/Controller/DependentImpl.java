package com.backend.Employee2.Controller;

import com.backend.Employee2.Dtos.DependentDto;
import org.springframework.http.ResponseEntity;

public interface DependentImpl {
    public ResponseEntity<DependentDto> addDependent(final DependentDto dependentDto);
    public ResponseEntity<DependentDto> getDependent(final long id );
}
