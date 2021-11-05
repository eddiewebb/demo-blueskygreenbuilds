#!/bin/bash
./mvnw compile jib:build -B -DskipTests=true \
              -Dbuild.number=local \
              -Dcommit.hash=1 \
              -Dcircle.workflow.guid=1 \
              -Dbuild.user=local \
              -Dbuild.repo=local

docker run -p8080:8080 eddiewebb/blueskygreenbuilds-demo:blocal