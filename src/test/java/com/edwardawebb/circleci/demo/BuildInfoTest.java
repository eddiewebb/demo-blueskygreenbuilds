package com.edwardawebb.circleci.demo;

import org.junit.jupiter.api.*;

public class BuildInfoTest {
    @Nested
    @DisplayName("Tests for the method getWorkflowUrl")
    class getWorkflowUrlTests {
        @Test
        void test1() {
            BuildInfo object = new BuildInfo();
            object.getWorkflowUrl();
        }
    }
    @Nested
    @DisplayName("Tests for the method getWorkflowGuid")
    class getWorkflowGuidTests {
        @Test
        void test1() {
            BuildInfo object = new BuildInfo();
            object.getWorkflowGuid();
        }
    }
    @Nested
    @DisplayName("Tests for the method setWorkflowGuid")
    class setWorkflowGuidTests {
        @Test
        void test1() {
            BuildInfo object = new BuildInfo();
            object.setWorkflowGuid("data:image/svg+xml;charset=UTF-8,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20version%3D%221.1%22%20baseProfile%3D%22full%22%20width%3D%22undefined%22%20height%3D%22undefined%22%3E%3Crect%20width%3D%22100%25%22%20height%3D%22100%25%22%20fill%3D%22grey%22%2F%3E%3Ctext%20x%3D%22NaN%22%20y%3D%22NaN%22%20font-size%3D%2220%22%20alignment-baseline%3D%22middle%22%20text-anchor%3D%22middle%22%20fill%3D%22white%22%3Eundefinedxundefined%3C%2Ftext%3E%3C%2Fsvg%3E");
        }
        @Test
        void test2() {
            BuildInfo object = new BuildInfo();
            object.setWorkflowGuid("");
        }
    }
    @Nested
    @DisplayName("Tests for the method setCommitHash")
    class setCommitHashTests {
        @Test
        void test1() {
            BuildInfo object = new BuildInfo();
            object.setCommitHash("Expressway");
        }
        @Test
        void test2() {
            BuildInfo object = new BuildInfo();
            object.setCommitHash("Lights");
        }
        @Test
        void test3() {
            BuildInfo object = new BuildInfo();
            object.setCommitHash("Extensions");
        }
        @Test
        void test4() {
            BuildInfo object = new BuildInfo();
            object.setCommitHash("Harbors");
        }
        @Test
        void test5() {
            BuildInfo object = new BuildInfo();
            object.setCommitHash("Port");
        }
        @Test
        void test6() {
            BuildInfo object = new BuildInfo();
            object.setCommitHash("");
        }
    }
    @Nested
    @DisplayName("Tests for the method getCommitHash")
    class getCommitHashTests {
        @Test
        void test1() {
            BuildInfo object = new BuildInfo();
            object.getCommitHash();
        }
    }
    @Nested
    @DisplayName("Tests for the method getCommitUser")
    class getCommitUserTests {
        @Test
        void test1() {
            BuildInfo object = new BuildInfo();
            object.getCommitUser();
        }
    }
    @Nested
    @DisplayName("Tests for the method getRepoName")
    class getRepoNameTests {
        @Test
        void test1() {
            BuildInfo object = new BuildInfo();
            object.getRepoName();
        }
    }
    @Nested
    @DisplayName("Tests for the method setRepoName")
    class setRepoNameTests {
        @Test
        void test1() {
            BuildInfo object = new BuildInfo();
            object.setRepoName("AM32WSU");
        }
        @Test
        void test2() {
            BuildInfo object = new BuildInfo();
            object.setRepoName("LE53KBN");
        }
        @Test
        void test3() {
            BuildInfo object = new BuildInfo();
            object.setRepoName("HR47NOU");
        }
        @Test
        void test4() {
            BuildInfo object = new BuildInfo();
            object.setRepoName("IM68JBJ");
        }
        @Test
        void test5() {
            BuildInfo object = new BuildInfo();
            object.setRepoName("UP72NWV");
        }
        @Test
        void test6() {
            BuildInfo object = new BuildInfo();
            object.setRepoName("");
        }
    }
    @Nested
    @DisplayName("Tests for the method setCommitUser")
    class setCommitUserTests {
        @Test
        void test1() {
            BuildInfo object = new BuildInfo();
            object.setCommitUser("Michael");
        }
        @Test
        void test2() {
            BuildInfo object = new BuildInfo();
            object.setCommitUser("Edmond");
        }
        @Test
        void test3() {
            BuildInfo object = new BuildInfo();
            object.setCommitUser("Jean-Philippe");
        }
        @Test
        void test4() {
            BuildInfo object = new BuildInfo();
            object.setCommitUser("Pierre Edouard");
        }
        @Test
        void test5() {
            BuildInfo object = new BuildInfo();
            object.setCommitUser("Anas");
        }
        @Test
        void test6() {
            BuildInfo object = new BuildInfo();
            object.setCommitUser("");
        }
    }
    @Nested
    @DisplayName("Tests for the method getGithubUrl")
    class getGithubUrlTests {
        @Test
        void test1() {
            BuildInfo object = new BuildInfo();
            object.getGithubUrl();
        }
    }
    @Nested
    @DisplayName("Tests for the method getBuildNum")
    class getBuildNumTests {
        @Test
        void test1() {
            BuildInfo object = new BuildInfo();
            object.getBuildNum();
        }
    }
    @Nested
    @DisplayName("Tests for the method getPodInfo")
    class getPodInfoTests {
        @Test
        void test1() {
            BuildInfo object = new BuildInfo();
            object.getPodInfo();
        }
    }
    @Nested
    @DisplayName("Tests for the method setBuildNum")
    class setBuildNumTests {
        @Test
        void test1() {
            BuildInfo object = new BuildInfo();
            object.setBuildNum("59089");
        }
        @Test
        void test2() {
            BuildInfo object = new BuildInfo();
            object.setBuildNum("43083");
        }
        @Test
        void test3() {
            BuildInfo object = new BuildInfo();
            object.setBuildNum("64832");
        }
        @Test
        void test4() {
            BuildInfo object = new BuildInfo();
            object.setBuildNum("69660");
        }
        @Test
        void test5() {
            BuildInfo object = new BuildInfo();
            object.setBuildNum("");
        }
    }
    @Nested
    @DisplayName("Tests for the method formatGithubUrl")
    class formatGithubUrlTests {
        @Test
        void test1() {
            BuildInfo object = new BuildInfo();
            object.formatGithubUrl("Pierre Edouard","protocol-reboot","Harbors");
        }
        @Test
        void test2() {
            BuildInfo object = new BuildInfo();
            object.formatGithubUrl("Michael","sensor-copy","Lights");
        }
        @Test
        void test3() {
            BuildInfo object = new BuildInfo();
            object.formatGithubUrl("Anas","sensor-copy","Port");
        }
        @Test
        void test4() {
            BuildInfo object = new BuildInfo();
            object.formatGithubUrl("Edmond","microchip-bypass","Harbors");
        }
        @Test
        void test5() {
            BuildInfo object = new BuildInfo();
            object.formatGithubUrl("Anas","sensor-copy","Harbors");
        }
        @Test
        void test6() {
            BuildInfo object = new BuildInfo();
            object.formatGithubUrl("","","");
        }
    }
}
