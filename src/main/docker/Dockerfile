FROM centos

MAINTAINER suren

USER root

RUN yum update -y
RUN yum install -y vim
RUN yum install -y java-1.8.0-openjdk-devel
RUN yum install -y wget
RUN yum install -y unzip

RUN useradd --create-home suren

USER suren
WORKDIR /home/suren
RUN mkdir phoenix

RUN printf "alias l='ls -hal'\n" >> ~/.bashrc
RUN printf "set number\n" >> ~/.vimrc
RUN printf "set incsearch\n" >> ~/.vimrc
RUN printf "set ignorecase\n" >> ~/.vimrc
RUN printf "set autoindent\n" >> ~/.vimrc

WORKDIR /home/suren/phoenix

RUN wget http://mirror.bit.edu.cn/apache/tomcat/tomcat-8/v8.5.16/bin/apache-tomcat-8.5.16.zip
RUN unzip apache-tomcat-8.5.16.zip
RUN chmod u+x apache-tomcat-8.5.16/bin/*.sh
RUN rm -rfv apache-tomcat-8.5.16/webapps/*
RUN rm -rfv apache-tomcat-8.5.16.zip

ADD phoenix.war apache-tomcat-8.5.16/webapps/phoenix.war

ENTRYPOINT [ "sh", "-c", "apache-tomcat-8.5.16/bin/catalina.sh run" ]