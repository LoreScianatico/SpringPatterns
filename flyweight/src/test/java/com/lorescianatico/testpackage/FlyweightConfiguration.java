package com.lorescianatico.testpackage;

import com.lorescianatico.patterns.flyweight.FlyweightFactory;
import com.lorescianatico.patterns.flyweight.FlyweightFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlyweightConfiguration {

    @Bean
    public FlyweightFactory<String, String> flyweightFactory(){
        return new FlyweightFactoryImpl<>();
    }

}
