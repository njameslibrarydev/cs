FROM java:8
VOLUME /tmp
ADD target/customer-management-1.0.0.jar customer-management.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/customer-management.jar"]
