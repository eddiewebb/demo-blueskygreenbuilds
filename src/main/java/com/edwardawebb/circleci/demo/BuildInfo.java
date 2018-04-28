package com.edwardawebb.circleci.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BuildInfo {

    @Value("${CF_INSTANCE_INDEX}")
    private String index;

    @Value("${circle_build_num}")
    private String buildNum;


    @Value("${circle_commit}")
    private String commitHash;

    @Value("${CF_INSTANCE_GUID}")
    private String cfGuid;

    @Value("${vcap.application.name}")
    private String applicationName;

    @Value("${circle_workflow_guid}")
    private String workflowGuid;

    protected static final String URL_PREFIX="https://circleci.com/workflow-run/";

    public BuildInfo() {
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getCfGuid() {
        return cfGuid;
    }

    public void setCfGuid(String guid) {
        this.cfGuid = guid;
    }
    public String getWorkflowGuid() {
        return workflowGuid;
    }

    public void setWorkflowGuid(String workflowGuid) {
        this.workflowGuid = workflowGuid;
    }

    public String getWorkflowUrl(){
        return URL_PREFIX + workflowGuid;
    }


    public String getCommitHash() {
        return commitHash;
    }

    public void setCommitHash(String commitHash) {
        this.commitHash = commitHash;
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
