#! /usr/bin/bash
. ./00_SET_ENV

export PATH=$MAVEN/apache-maven-3.6.3/bin:$PATH
rm -rf $PROJECT_DIR
git clone $SOURCE_URL $PROJECT_DIR
mvn -Dmaven.test.skip=true -DfinalName=$JAR_NAME install -f $APP_DIR