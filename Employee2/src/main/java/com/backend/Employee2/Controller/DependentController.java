package com.backend.Employee2.Controller;

import ch.qos.logback.core.joran.action.PreconditionValidator;
import com.backend.Employee2.Dtos.DependentDto;
import com.backend.Employee2.Dtos.EmployeeDto;
import com.backend.Employee2.Model.Dependent;
import com.backend.Employee2.Model.Employee;
import com.backend.Employee2.Service.DependentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/dependents")
public class DependentController implements DependentImpl{
    private final DependentsService dependentsService;

    public DependentController(DependentsService dependentsService) {
        this.dependentsService = dependentsService;
    }

    @Override
    public ResponseEntity<List<DependentDto>> getAllDependents() {
        List<Dependent> dependents = dependentsService.getAllDependents();
        List<DependentDto> dependentDtos = dependents.stream().map(DependentDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(dependentDtos, HttpStatus.OK);
    }

    @Override
    @DeleteMapping(value = "{id}")
    public ResponseEntity<DependentDto> deleteDependent(@PathVariable final long id ) {
        Dependent dependent = dependentsService.deleteDependent(id);
        return new ResponseEntity<>(DependentDto.from(dependent), HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<DependentDto> addDependent(@RequestBody final DependentDto dependentDto ) {
        Dependent dependents  = dependentsService.addDependents(Dependent.from(dependentDto));
        return  new ResponseEntity<>(DependentDto.from(dependents), HttpStatus.OK);
    }

    @Override
    @GetMapping(value =  "{id}")
    public ResponseEntity<DependentDto> getDependent(@PathVariable final long id ) {
        Dependent dependent = dependentsService.getDependent(id);
        return new ResponseEntity<>(DependentDto.from(dependent), HttpStatus.OK);
    }



}

