# Demoing CircleCI

1. Run runDemo.py from project root.



## Script Flow (do before/to start demo)
1. Delete any previous issues (includes PRs)
2. Checkout master, force test_case to passing state
3. Create issue
4. create branch with issue name
5. Break test case, commit against issue
6. Open PR of branch against Master


## Demo Flow
After running script above the repo will have a single PR and issue, both showing failures in CCI

1. Show PRs/Issues, visibility into health/codebase
2. Jump to CCI, show test failure (show more) jump to line in codebase
3. Fix (comment) test failure, commit, push
4. Show build triggered in UI, unit tests should pass very quick, longer running UAT trigger.
4.  SSH FIXES (use failed unit tes, suppose I didnt know what was wrong....)
5. Use this time to show configuration as code.
 - lives in codebase with code
 - fully deterministic
 - Ultimately flexible
6. See Workflow passing (should get chrome notification)
7. Reload PR overview page,  PR details

- Filters, Tags, RegExp
- Caching
- ChatOps
- IMage per job
8. Related issue, specific commits

## Chat OPs - per project settings

## Image per job
##
