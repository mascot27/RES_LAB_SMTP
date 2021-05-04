# dockerfile for containerization of MockMock mail server
# Done for for lab 3 of RES course given at HEIG-VD
# original project: https://github.com/tweakers/MockMock
# 
# authors of this file: Laurent Thaillades, Corentin Zeller
# Date: 4 may 2021

FROM java:8
MAINTAINER CZeller contact@corentin-zeller.ch

# Add files
ADD src /opt/src

WORKDIR /opt/src

ENTRYPOINT ["java","-jar","MockMock.jar", "-p", "2525"]

