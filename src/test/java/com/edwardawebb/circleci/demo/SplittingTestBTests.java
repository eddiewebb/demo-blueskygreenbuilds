package com.edwardawebb.circleci.demo;

import org.junit.Test;

public class SplittingTestBTests {


    @Test
    public void testThatTakesOneTenthSeconds() throws Exception {
        Thread.sleep(1000);
    }

    @Test
    public void testThatTakesTwoTenthSeconds() throws Exception {
        Thread.sleep(2000);
    }

    @Test
    public void testThatTakesThreeTenthSeconds() throws Exception {
        Thread.sleep(3000);
    }

    @Test
    public void testThatTakesFourTenthSeconds() throws Exception {
        Thread.sleep(4000);
    }


}
