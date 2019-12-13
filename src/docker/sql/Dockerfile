#Derive from official mysql image
FROM mysql

#ROOT VARAIBLES
ENV MYSQL_ROOT_PASSWORD=password

#add script
COPY ./sql-scripts/ /docker-entrypoint-initdb.d/