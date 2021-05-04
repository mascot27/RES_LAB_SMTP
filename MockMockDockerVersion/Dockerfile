# dockerfile for containerization of MockMock mail server
# Done for for lab 3 of RES course given at HEIG-VD
# original project: https://github.com/tweakers/MockMock
# 
# authors of this file: Laurent Thaillades, Corentin Zeller
# Date: 4 may 2021

FROM openjdk:8
MAINTAINER CZeller contact@corentin-zeller.ch

WORKDIR /app

ENV SMTP_PORT=25
ENV HTTP_PORT=8282

# Clone original project GitHub repository
RUN git clone https://github.com/tweakers/MockMock
WORKDIR /mockmock/

# expose smtp and web panel's ports
EXPOSE 25
EXPOSE 8282

ENTRYPOINT java -jar MockMock.jar -p $SMTP_PORT -h $HTTP_PORT
