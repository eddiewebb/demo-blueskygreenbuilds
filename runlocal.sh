#!/bin/bash
./mvnw compile jib:build -B -DskipTests=true \
              -Dbuild.number=local \
              -Dcommit.hash=1 \
              -Dcircle.workflow.guid=1 \
              -Dbuild.user=local \
              -Dbuild.repo=local

#refresh image
docker pull eddiewebb/blueskygreenbuilds-demo:blocal

# to run the container locally we mimic envvars poassed by k8s
docker run -p8080:8080 -e MY_POD_NAME=local -e MY_POD_IP="1234" eddiewebb/blueskygreenbuilds-demo:blocal