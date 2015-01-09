#!/bin/sh
cd -- "$(dirname "$0")"
mvn install:install-file -Dfile=bcel-6.0-SNAPSHOT.jar -DgroupId=com.google.code.findbugs -DartifactId=bcel -Dversion=6.0-SNAPSHOT -Dpackaging=jar
mvn install:install-file -Dfile=findbugs-3.0.1-SNAPSHOT-20150109.jar -DpomFile=findbugs-3.0.1-SNAPSHOT-20150109.pom

