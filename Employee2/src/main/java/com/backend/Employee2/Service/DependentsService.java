package com.backend.Employee2.Service;

import com.backend.Employee2.Model.Dependent;
import com.backend.Employee2.Model.Exception.DependentNotFoundException;
import com.backend.Employee2.Repository.DependentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DependentsService {

    private final DependentRepository dependentRepository;

    @Autowired
    public DependentsService(DependentRepository depenedentRepository) {
        this.dependentRepository = depenedentRepository;
    }

    public List<Dependent> getAllDependents() {
        return StreamSupport.stream(dependentRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Dependent deleteDependent(long id) {
        Dependent dependent = getDependent(id);
        dependentRepository.delete(dependent);
        return dependent;
    }

    public Dependent addDependents(Dependent dependents) {
        return dependentRepository.save(dependents);
    }

    public Dependent getDependent(Long id) {
         return  dependentRepository.findById(id).orElseThrow(() -> new DependentNotFoundException(id));
    }
}
