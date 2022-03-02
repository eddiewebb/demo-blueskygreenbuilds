package com.edwardawebb.circleci.demo;

import org.junit.jupiter.api.Test;

public class SplittingTestBTests {


    @Test
    public void testThatTakesOneTenthSeconds() throws Exception {
        Thread.sleep(100);
    }

    @Test
    public void testThatTakesTwoTenthSeconds() throws Exception {
        Thread.sleep(200);
    }

    @Test
    public void testThatTakesThreeTenthSeconds() throws Exception {
        Thread.sleep(300);
    }

    @Test
    public void testThatTakesFourTenthSeconds() throws Exception {
        Thread.sleep(400);
    }


}
