package com.edwardawebb.circleci.demo.controllers;

import com.edwardawebb.circleci.demo.*;

import org.junit.jupiter.api.*;

public class RestControllerIT {
    @Nested
    @DisplayName("Tests for the method greeting")
    class greetingTests {
        @Test
        void test1() {
            BuildInfo object = new BuildInfo();
            RestController object2 = new RestController(object);
            object2.greeting();
        }
    }
}
