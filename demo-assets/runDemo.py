#!/usr/bin/env python3
import os
import requests
import re
from subprocess import call

starting_hash='df0130d4b7b27fb48cc78641f801f529a1c1abb6'

github_user='eddiewebb'
github_token=os.environ['GITHUB_API_TOKEN']
github_repo='demo-blueskygreenbuilds'
auth=(github_user,github_token)


base_url='https://api.github.com/repos/{github_user}/{github_repo}'.format(**locals())


test_case='src/test/java/com/edwardawebb/circleci/demo/BuildInfoControllerTests.java'


def main():
    revertToKnownCleanState()
    issue=newDemoIssueId()
    branch=newDemoBranch(issue)
    uncommentTestFailure()
    commitLocalChangeAgainstIssue(branch,issue,"Breaks issue #" + str(issue['number']) + " with failing test.")
    pr=openPullRequestAgainstBranch(branch,issue)
    print("PR: " + pr['html_url'] + " created")
    input("Press Enter to commit fix...")
    commentTestFailure()
    commitLocalChangeAgainstIssue(branch,issue,"Fixes issue #" + str(issue['number']) + ", tests passing.")
    print("PR: " + pr['html_url'] + " will be closed if still open")
    input("Press enter to checkout latest from main (reset)")
    mergePullRequestIfOpen(pr)
    revertToKnownCleanState()



print("using base URL: " + base_url )

def revertToKnownCleanState():
    # close open issues
    closeAllGithubeIssues()

    # revert test_case
    call(['git','checkout','main'])
    call(['git','pull'])
    call(['git','checkout',starting_hash,'--',test_case])
    call(['git','commit','-am','"Revert test cases to passing state [skip ci]"'])
    call(['git','push','--force'])

    # remove any branches

def closeAllGithubeIssues():
    params={'state':'open'}
    r = requests.get(base_url+'/issues',params=params,auth=auth)
    if r.status_code == 200:
        for issue in r.json():
            print("Closing issue: " + issue['url'])
            closeGithubClosable(issue)
    else:
        print("error contactubg GH api")
        exit(1)

def closeGithubClosable(closable):
    status={'state':'closed'}
    r = requests.patch(closable['url'],json=status,auth=auth)
    if r.status_code == 200:
        print("\t closed")
        return r.json()
    else:
        print("error contactubg GH api")
        exit(1)

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
    with open(test_case, "r") as sources:
        lines = sources.readlines()
    with open(test_case, "w") as sources:
        for line in lines:
            sources.write(re.sub(r'\/\/fail', 'fail', line))


def commentTestFailure():
    with open(test_case, "r") as sources:
        lines = sources.readlines()
    with open(test_case, "w") as sources:
        for line in lines:
            sources.write(re.sub(r'fail\(', '//fail(', line))

def commitLocalChangeAgainstIssue(branch_name,  issue, commit_message):
    call(['git','commit','-am',commit_message])
    call(['git','push','--set-upstream','origin',branch_name])

def openPullRequestAgainstBranch(branch_name, issue):
    pull_request={
        'title':'Merge ' + branch_name + ' into production stream',
        'head':branch_name,
        'base':'main',
        'body':'Please review and merge changes for Issue #' +str(issue['number'])
    }
    r = requests.post(base_url+'/pulls',json=pull_request,auth=auth)
    if r.status_code == 201:
        return r.json()
    else:
        print("error contacting GH api")
        exit(1)


def mergePullRequestIfOpen(pr):
    merge_details={
        'commit_title':'Merging hanging PR from demo.',
        'commit_message':'This PR was left open during a demo, and was forced merge upon completion',
    }
    r = requests.put(base_url+'/pulls/' + str(pr['number']) + '/merge' ,json=merge_details,auth=auth)
    if r.status_code == 200:
        return r.json()
    else:
        print("error contacting GH api")
        print(r.json())
        exit(1)

main()

