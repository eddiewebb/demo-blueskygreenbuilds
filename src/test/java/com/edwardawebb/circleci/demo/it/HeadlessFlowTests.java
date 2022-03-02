package com.edwardawebb.circleci.demo.it;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.extension.ExtendWith;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.test.context.junit.jupiter.SpringExtension;


/**
 * This is not a local test, but is intended to be paired with Rigor to execute remote tests. It will be ignored thorugh normal test lifecycle.
 *  To run explicitly, use ./mvnw -Dtest=HeadlessFlowTests test -Dtest-groups=rigor -DbaseURL="http://dark.blueskygreenbuilds.com"
 */
@ExtendWith(SpringExtension.class)
@EnabledIfSystemProperty(named="test-groups", matches="rigor")
public class HeadlessFlowTests {

    private String baseURL;

    private WebClient webClient;

    @BeforeEach
    public void setup() {
        baseURL = System.getProperty("baseURL","http://blueskygreenbuilds.com");
        System.err.println("Using: " + baseURL);
        webClient = new WebClient();
    }

    @AfterEach
    public void close() {
        webClient.close();
    }

    @Test
    public void homePageHtmlUnit() throws Exception {
        HtmlPage currentPage = webClient.getPage(baseURL);
        assertEquals("Blue Sky, Green Builds", currentPage.getTitleText());
        HtmlAnchor link = (HtmlAnchor) currentPage.getElementById("car-btn-1");
        HtmlPage result = link.click();
        assertThat( result.getBody().getTextContent(), containsString("Start Today"));
    }
}
