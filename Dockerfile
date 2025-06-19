# Usa una imagen base de WildFly 28
FROM quay.io/wildfly/wildfly:28.0.1.Final-jdk17

# Copia el archivo WAR de tu aplicación al directorio de despliegue de WildFly
ARG WAR_FILE=./target/*.war
COPY ${WAR_FILE} /opt/jboss/wildfly/standalone/deployments/

# Exponer el puerto HTTP
EXPOSE 8080

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "--server-config=standalone.xml"]
