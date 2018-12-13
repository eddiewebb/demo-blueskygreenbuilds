package com.edwardawebb.circleci.demo;

import org.junit.Test;

/**
 *  This class has slow tests, and if timing is working, should get a dedicated node while circleci gives all other classes to parrallel node.
 */
public class SplittingTestATests {


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
