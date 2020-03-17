package com.lorescianatico.patterns.facade;

@Facade("testFacadeImpl")
public class TestFacadeImpl implements TestFacade{

    @Override
    public int getOne() {
        return 1;
    }

}
