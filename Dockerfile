# we are extending everything from tomcat:9.0.27 image ...
FROM tomcat:9.0.27
MAINTAINER willy

RUN mkdir -p /usr/local/tomcat/conf

# COPY path-to-your-application-war path-to-webapps-in-docker-tomcat
COPY target/ws-bank-1.0.war /usr/local/tomcat/webapps/
COPY tomcat-users.xml /usr/local/tomcat/conf/
COPY context.xml /usr/local/tomcat/webapps/manager/META-INF/
