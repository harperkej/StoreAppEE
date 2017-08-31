#!/bin/bash

#Start domain1 first
asadmin start-domain domain1 && \
	asadmin create-jdbc-connection-pool \
	--datasourceclassname com.mysql.jdbc.jdbc2.optional.MysqlDataSource \
	--restype javax.sql.DataSource \
	--property user=root:password=root:DatabaseName=buyproducts:ServerName=$DATABASE_IP:port=3306 mysql_pool && \
 	asadmin create-jdbc-resource --connectionpoolid mysql_pool jdbc/shop_ee_datasource && \
        asadmin --user $ADMIN_USER --passwordfile=Old_Password.txt change-admin-password && \
        asadmin --user $ADMIN_USER --passwordfile=New_Password.txt enable-secure-admin && \
	asadmin stop-domain domain1 && \
	asadmin start-domain domain1

while true
do
	sleep 10000000
done
