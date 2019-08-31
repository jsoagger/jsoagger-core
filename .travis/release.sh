#!/usr/bin/env bash

set -e

echo "Deploying release version to SONATYPE"
mvn -X clean deploy --settings .maven.xml -DskipTests=true -Dmaven.javadoc.skip=true -B -U -Prelease

# log is truncated when build fails
# try this work arround
sleep 1



