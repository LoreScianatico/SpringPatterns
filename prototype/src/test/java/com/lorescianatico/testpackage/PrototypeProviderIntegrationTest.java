package com.lorescianatico.testpackage;

import com.lorescianatico.patterns.prototype.PrototypeProvider;
import com.lorescianatico.patterns.prototype.PrototypeProviderConfiguration;
import com.lorescianatico.patterns.prototype.PrototypeProviderImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@Import(PrototypeProviderConfiguration.class)
public class PrototypeProviderIntegrationTest {

    @Autowired
    private PrototypeProvider prototypeProvider;

    @Test
    void testIntegration(){
        assertNotNull(prototypeProvider);
        assertTrue(prototypeProvider instanceof PrototypeProviderImpl);
    }

}
