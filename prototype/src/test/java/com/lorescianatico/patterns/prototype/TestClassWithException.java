package com.lorescianatico.patterns.prototype;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TestClassWithException {

    private String field = "value";

    public void setField(String field) {
        this.field = field;
        throw new RuntimeException();
    }
}
