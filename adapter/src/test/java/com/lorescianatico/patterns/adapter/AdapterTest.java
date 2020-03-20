package com.lorescianatico.patterns.adapter;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class AdapterTest {

    @Test
    void adapt() {
        LegacyService legacyService = new LegacyService();
        NewService legacyServiceAdapter = Adapter.adapt(NewService.class)
                .adaptMethod("getAnInteger", legacyService::returnInt)
                .build();
        Integer i = legacyServiceAdapter.getAnInteger();
        logger.info("Returned value: {}", i);
        Assertions.assertNotNull(i);

    }
}