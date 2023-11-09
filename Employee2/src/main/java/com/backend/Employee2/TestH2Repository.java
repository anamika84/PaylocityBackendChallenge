package com.backend.Employee2;

import com.backend.Employee2.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestH2Repository extends JpaRepository<Employee, Long> {
}
