FROM harperkej/java8

LABEL maintainer="Arber Kuci, arber.kuci2@gmail.com"

RUN chmod a+x /opt

ENV ADMIN_USER admin
ENV ADMIN_PASSWORD admin

ENV DATABASE_IP 172.17.0.2

ENV PAYARA_DOWNLOAD_URL https://s3-eu-west-1.amazonaws.com/payara.co/Payara+Downloads/Payara+4.1.1.161/payara-4.1.1.161.zip
ENV PAYARA_VERSION 161
ENV PAYARA_FILE_NAME payara-full-${PAYARA_VERSION}.zip
ENV PAYARA_DIR /opt/payara41

RUN wget -O /opt/${PAYARA_FILE_NAME} ${PAYARA_DOWNLOAD_URL}
RUN unzip /opt/${PAYARA_FILE_NAME} -d /opt && \
    rm /opt/${PAYARA_FILE_NAME}

#Download mysql java connector and putting it to proper application server directory.

ENV MYSQL_CONNECTOR_DOWNLOAD_URL https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java-5.1.43.zip
ENV MYSQL_CONNECTOR_VERSION 5.1.43
ENV MYSQL_CONNECTOR_FILE_NAME mysql-connector-java-${MYSQL_CONNECTOR_VERSION}

RUN wget -O /opt/${MYSQL_CONNECTOR_FILE_NAME}.zip ${MYSQL_CONNECTOR_DOWNLOAD_URL}
RUN unzip /opt/${MYSQL_CONNECTOR_FILE_NAME}.zip -d /opt && \
    mv /opt/${MYSQL_CONNECTOR_FILE_NAME}/mysql-connector-java-5.1.43-bin.jar ${PAYARA_DIR}/glassfish/domains/domain1/lib && \
    rm -r /opt/${MYSQL_CONNECTOR_FILE_NAME}

ENV PAYARA_DEPLOYMENT_DIR ${PAYARA_DIR}/deployments

RUN mkdir PAYARA_DEPLOYMENT_DIR

WORKDIR ${PAYARA_DIR}/bin/

COPY StoreApp-ear.ear .

#Admin user should have a different password from the defualt(empty) one so that remote administration is enables...

ADD New_Password.txt .
ADD Old_Password.txt .

ADD start.sh .

RUN chmod a+x start.sh

ENTRYPOINT start.sh

EXPOSE 4848 8080 8009 8181
