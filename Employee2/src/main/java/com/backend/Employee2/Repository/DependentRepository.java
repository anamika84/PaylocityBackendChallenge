package com.backend.Employee2.Repository;

import com.backend.Employee2.Model.Dependent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DependentRepository extends CrudRepository<Dependent, Long> {
}
