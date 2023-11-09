package com.backend.Employee2.Controller;

import com.backend.Employee2.Dtos.DependentDto;
import com.backend.Employee2.Dtos.EmployeeDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DependentImpl {
    public ResponseEntity<DependentDto> addDependent(final DependentDto dependentDto);
    public ResponseEntity<DependentDto> getDependent(final long id );
    ResponseEntity<List<DependentDto>> getAllDependents();
    public ResponseEntity<DependentDto> deleteDependent(long id);

}
