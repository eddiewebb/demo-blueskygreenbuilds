package com.edwardawebb.circleci.demo;

import com.edwardawebb.circleci.demo.controllers.HomeController;
import org.junit.Test; 

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.MockClock;
import io.micrometer.core.instrument.simple.SimpleConfig;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;

public class HomeControllerTests {

    private final MeterRegistry registry = new SimpleMeterRegistry(SimpleConfig.DEFAULT, new MockClock());
    
    @Test
    public void testCorrectRouteForAbout() throws Exception {
        HomeController controller = new HomeController(registry);
        String route = controller.about(null);
        assertThat("Home page route did not match",route,is("about"));
        
    }



}
