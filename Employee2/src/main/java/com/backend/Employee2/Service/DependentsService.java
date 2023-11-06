package com.backend.Employee2.Service;

import com.backend.Employee2.Model.Dependent;
import com.backend.Employee2.Model.Exception.DependentNotFoundException;
import com.backend.Employee2.Repository.DependentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DependentsService {

    private final DependentRepository dependentRepository;

    @Autowired
    public DependentsService(DependentRepository depenedentRepository) {
        this.dependentRepository = depenedentRepository;
    }

    public Dependent addDependents(Dependent dependents) {
        return dependentRepository.save(dependents);
    }

    public Dependent getDependent(Long id) {
         return  dependentRepository.findById(id).orElseThrow(() -> new DependentNotFoundException(id));
    }
}
