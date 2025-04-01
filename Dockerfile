FROM openjdk:17
LABEL authors="zaz"
VOLUME /tmp
ADD target/service-vm-offers.jar service-vm-offers.jar
ENTRYPOINT [ "java","-jar","/service-vm-offers.jar" ]