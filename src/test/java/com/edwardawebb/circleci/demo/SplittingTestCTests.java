package com.edwardawebb.circleci.demo;

import org.junit.Test;

public class SplittingTestCTests {


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


    @Test
    public void testThatTakesOneSeconds() throws Exception {
        Thread.sleep(1000);
    }

    @Test
    public void testThatTakesTwoSeconds() throws Exception {
        Thread.sleep(2000);
    }

    @Test
    public void testThatTakesThreeSeconds() throws Exception {
        Thread.sleep(3000);
    }

    @Test
    public void testThatTakesFourSeconds() throws Exception {
        Thread.sleep(4000);
    }


}
