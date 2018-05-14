package com.edwardawebb.circleci.demo;

import org.junit.Test;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class BuildInfoTests {

    private static final String WORKFLOW_URL_NAME = "workflowUrl" ;

    @Test
    public void testBuildInfoPreservesValues() throws IntrospectionException {
        BuildInfo buildInfo = new BuildInfo();
        Arrays.asList(Introspector.getBeanInfo(buildInfo.getClass(), Object.class)
                .getPropertyDescriptors())
                .stream()
                // filter out properties with setters only
                .filter(pd -> Objects.nonNull(pd.getReadMethod()))
                .forEach(pd -> { // invoke method to get value
                    try {
                        System.out.println("TESTING : " + pd.getName());
                        validateGetter(pd, buildInfo);
                    } catch (Exception e) {
                        // add proper error handling here
                        fail("Exception: " +e.getMessage());

                    }
                });
    }

    private void validateGetter(PropertyDescriptor pd, BuildInfo buildInfo) {
        String value = UUID.randomUUID().toString();
        if(pd.getName().equals(WORKFLOW_URL_NAME)){
            System.out.println("--> no write, expecting modified value from workflowGuid");
            value = BuildInfo.URL_PREFIX + buildInfo.getWorkflowGuid();
        }else{
            System.out.println("--> writing random value");
            try {
                pd.getWriteMethod().invoke(buildInfo,value); // will fail if matching getter is missing
            } catch (IllegalAccessException e) {
                fail("Write method not found!");
            } catch (InvocationTargetException e) {
                fail("Write method not found!");
            }
        }

        String returned = null;
        try {
            returned = (String) pd.getReadMethod().invoke(buildInfo);
        } catch (Exception e) {
            System.out.println("read method not found!");
            e.printStackTrace();
        }

        System.out.println("<-- Reading for value: " + value);

        assertThat("Value null",returned,notNullValue());
        assertThat("Values doesnt match",returned,is(value));
        System.out.println("   == Match");

    }
}
