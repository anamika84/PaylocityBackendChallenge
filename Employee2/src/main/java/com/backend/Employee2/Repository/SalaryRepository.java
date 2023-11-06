package com.backend.Employee2.Repository;

import com.backend.Employee2.Model.Salary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryRepository extends CrudRepository<Salary,Long> {
    @Query("select s from Salary s where s.employee.id = :id")
    List<Salary> findSalariesByEmployee_Id(Long id);
}

