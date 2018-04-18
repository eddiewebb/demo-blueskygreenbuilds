package com.edwardawebb.circleci.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BuildInfo {

    @Value("${CF_INSTANCE_INDEX}")
    private String index;

    @Value("${circle_build_num}")
    private String buildNum;

    @Value("${CF_INSTANCE_GUID}")
    private String guid;

    @Value("${vcap.application.name}")
    private String applicationName;

    public BuildInfo() {
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getBuildNum() {
        return buildNum;
    }

    public void setBuildNum(String buildNum) {
        this.buildNum = buildNum;
    }
}
