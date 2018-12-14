package com.edwardawebb.circleci.demo;

import org.junit.Test;

/**
 *  This class has slow tests, and if timing is working, should get a dedicated node while circleci gives all other classes to parrallel node.
 */
public class AClassOfVeryVerySlowTests {


    @Test
    public void testThatTakesOneSeconds() throws Exception {
        Thread.sleep(1000);
    }

    @Test
    public void testThatTakesTwoSeconds() throws Exception {
        Thread.sleep(2000);
    }

    @Test
    public void testThatTakesOneHalfSeconds() throws Exception {
        Thread.sleep(500);
    }

    @Test
    public void testThatTakesOneQuarterSeconds() throws Exception {
        Thread.sleep(250);
    }

    @Test
    public void testThatTakeFiveSeconds() throws Exception {
        Thread.sleep(5000);
    }

    @Test
    public void testThatTakesOneSecondsAlso() throws Exception {
        Thread.sleep(1000);
    }

    @Test
    public void testThatTakesTwoSecondsAlso() throws Exception {
        Thread.sleep(2000);
    }

    @Test
    public void testThatTakesOneHalfSecondsAlso() throws Exception {
        Thread.sleep(500);
    }

    @Test
    public void testThatTakesOneQuarterSecondsAlso() throws Exception {
        Thread.sleep(250);
    }

    @Test
    public void testThatTakesOneSecondsAgain() throws Exception {
        Thread.sleep(1000);
    }

    @Test
    public void testThatTakesTwoSecondsAgain() throws Exception {
        Thread.sleep(2000);
    }

    @Test
    public void testThatTakesOneHalfSecondsAgain() throws Exception {
        Thread.sleep(500);
    }

    @Test
    public void testThatTakesOneQuarterSecondsAgain() throws Exception {
        Thread.sleep(250);
    }

}
