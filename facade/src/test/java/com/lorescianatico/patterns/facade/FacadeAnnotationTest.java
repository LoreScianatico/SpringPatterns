package com.lorescianatico.patterns.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestFacadeImpl.class})
public class FacadeAnnotationTest {

    @Autowired
    private TestFacade testFacade;

    @Test
    void testWorkingInjection(){
        assertNotNull(testFacade);
        assertTrue(testFacade instanceof TestFacadeImpl);
        assertEquals(1, testFacade.getOne());
    }

}
