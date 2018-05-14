#!/usr/bin/env python
import os
import requests
import re
from subprocess import call

github_user='eddiewebb'
github_token=os.environ['CCI_DEMO_GITHUB_API_TOKEN']
github_repo='demo-blueskygreenbuilds'
auth=(github_user,github_token)

base_url='https://api.github.com/repos/{github_user}/{github_repo}'.format(**locals())

print("using base URL: " + base_url )


def newDemoIssueId():
    print("running")
    new_issue={
        'title':"Demo: add functionality X to service Y",
        'body':"As a user I should be able to A so that B. I will be successful when O."
        }
    r = requests.post(base_url+'/issues',json=new_issue,auth=auth)
    if r.status_code == 201:
        return r.json()
    else:
        print("error contactubg GH api")
        exit(1)

def newDemoBranch(issue):
    branch_name='issue-'+str(issue['number'])
    print("Creating branch: " + branch_name)
    call(['git','checkout','-b',branch_name])
    return branch_name

def uncommentTestFailure():
    test_case='src/test/java/com/edwardawebb/circleci/demo/BuildInfoControllerTests.java'
    with open(test_case, "r") as sources:
        lines = sources.readlines()
    with open(test_case, "w") as sources:
        for line in lines:
            sources.write(re.sub(r'\/\/fail', 'fail', line))

def commitLocalChangeAgainstIssue(branch_name,  issue):
    commit_message="Fixes issue #" + str(issue['number']) + ", service now Y's properly."
    call(['git','commit','-am',commit_message])
    call(['git','push','--set-upstream','origin',branch_name])

def openPullRequestAgainstBranch(branch_name, issue):
    pull_request={
        'title':'Merge ' + branch_name + ' into production stream',
        'head':branch_name,
        'base':'master',
        'body':'Please review and merge changes for Issue #' +str(issue['number'])
    }
    r = requests.post(base_url+'/pulls',json=pull_request,auth=auth)
    if r.status_code == 201:
        return r.json()
    else:
        print("error contacting GH api")
        exit(1)


#revertToKnownCleanState
issue=newDemoIssueId()
branch=newDemoBranch(issue)
uncommentTestFailure()
commitLocalChangeAgainstIssue(branch,issue)
pr=openPullRequestAgainstBranch(branch,issue)
print("PR: " + pr['url'] + " created")
