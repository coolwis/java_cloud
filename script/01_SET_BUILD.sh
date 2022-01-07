#! /usr/bin/bash
. ./00_SET_ENV
yum -y install yum
yum -y install unzip
yum -y install java-11-openjdk java-11-openjdk-devel
yum -y install wget
mkdir -p $MAVEN
mkdir -p $APP_BASE
wget --directory-prefix=$MAVEN https://downloads.apache.org/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.zip
unzip -d $MAVEN $MAVEN/apache-maven-3.6.3-bin.zip