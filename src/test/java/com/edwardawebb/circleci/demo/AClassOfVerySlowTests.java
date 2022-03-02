package com.edwardawebb.circleci.demo;

import org.junit.jupiter.api.Test;

/**
 *  This class has slow tests, and if timing is working, should get a dedicated node while circleci gives all other classes to parrallel node.
 */
public class AClassOfVerySlowTests {


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


}
