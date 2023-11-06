package com.backend.Employee2;

import com.backend.Employee2.Model.Dependent;
import com.backend.Employee2.Repository.DependentRepository;
import com.backend.Employee2.Service.DependentsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DependentTest {
    @Mock
    private  DependentRepository dependentRepository;

    @InjectMocks
    private DependentsService dependentsService;

    @Test
    void getDependentTest() {
        Dependent dependent = new Dependent();
        dependent.setRelationType("Spouse");
        dependent.setFirstName("dp");
        dependent.setLastName("dn");
        when(dependentRepository.findById(any())).thenReturn(Optional.of(dependent));
        Dependent dependent1 = dependentsService.getDependent(1L);
        Assertions.assertEquals("dp", dependent1.getFirstName());
        Assertions.assertEquals("dn", dependent1.getLastName());

    }

}
