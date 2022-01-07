FROM centos
MAINTAINER wisdombrave@gmail.com
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENV LC_ALL=C.UTF-8
RUN yum -y install java-11-openjdk java-11-openjdk-devel
EXPOSE 8080