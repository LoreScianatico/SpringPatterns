package com.lorescianatico.testpackage;

import com.lorescianatico.patterns.flyweight.FlyweightFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Import(FlyweightConfiguration.class)
public class FlyweightIntegrationTest {

    @Autowired
    private FlyweightFactory<String, String> flyweightFactory;

    @Test
    void testIntegration(){
        Assertions.assertNotNull(flyweightFactory);
    }

}
