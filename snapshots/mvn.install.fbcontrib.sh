#!/bin/sh
cd -- "$(dirname "$0")"
mvn install:install-file -Dfile=fb-contrib-6.1.0-SNAPSHOT-20150107.jar -DpomFile=fb-contrib-6.1.0-SNAPSHOT-20150107.pom
