#!/bin/bash

case $1 in
reroute)
    # Send "real" url to new version
    cf map-route blueskygreenbuilds-dark blueskygreenbuilds.com -n www
    sleep 30  # give time to demo round robin
    # Stop sending traffic to previous version
    cf unmap-route blueskygreenbuilds blueskygreenbuilds.com -n www
    # stop previous version
    cf stop blueskygreenbuilds
    # delete previous version
    cf delete blueskygreenbuilds -f
    # Switch name of "dark" version to claim correct name
    cf rename blueskygreenbuilds-dark blueskygreenbuilds
    ;;
dark)
    cf push --no-start blueskygreenbuilds-dark -f cf-manifest.yml -p standalone-app.jar -n dark -d blueskygreenbuilds.com
    cf set-env blueskygreenbuilds-dark circle_build_num ${CIRCLE_BUILD_NUM}
    cf set-env blueskygreenbuilds-dark circle_commit ${CIRCLE_SHA1}
    # Push as "dark" instance (URL in manifest)
    cf start blueskygreenbuilds-dark
    # Ensure dark route is exclusive to dark app
    cf unmap-route blueskygreenbuilds blueskygreenbuilds.com -n dark || echo "Already exclusive"
    ;;
*)
    echo "Usage:  $0 (dark|reroute)"
    exit 1
    ;;
esac
