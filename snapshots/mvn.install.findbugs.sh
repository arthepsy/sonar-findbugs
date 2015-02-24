#!/bin/sh
cd -- "$(dirname "$0")"
mvn install:install-file -Dfile=findbugs-3.0.1-RC1.jar -DpomFile=findbugs-3.0.1-RC1.pom

