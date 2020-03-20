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
                .adaptMethod("addsOne", integer -> legacyService.plusOne((Integer) integer))
                .adaptMethod("getSomethingDifferent", integer -> legacyService.intToString((Integer) integer))
                .adaptMethod("sum", (a, b) -> legacyService.sumUp((int) a, (int) b))
                .build();
        Integer i = legacyServiceAdapter.getAnInteger();
        logger.info("Returned value: {}", i);
        Assertions.assertNotNull(i);
        Assertions.assertEquals(2, legacyServiceAdapter.addsOne(1));
        Assertions.assertEquals("3", legacyServiceAdapter.getSomethingDifferent(3));
        Assertions.assertThrows(NotAdaptedMethodException.class, legacyServiceAdapter::unadaptedOne);
    }
}