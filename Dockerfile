FROM maven AS build
RUN mkdir /app
WORKDIR /app
COPY ./ /app
RUN mvn clean package install

FROM openjdk:11.0.4-jre-slim
RUN mkdir /app
WORKDIR /app
COPY --from=build /app/target/api-usuario-telco-1.0.0.jar /app/api-usuario-telco-1.0.0.jar
#COPY ./target/api-usuario-telco-1.0.0.jar /app/api-usuario-telco-1.0.0.jar 
ENV DEFAULT_OPTIONS="-Duser.timezone=America/Guayaquil -Djava.net.preferIPv4Stack=true -Djava.security.egd=file:/dev/.urandom -DskipTests=true"
ENV JAVA_OPTS="-Xms512m -Xmx896m"
ENTRYPOINT java ${DEFAULT_OPTIONS} ${JAVA_OPTS} -jar api-usuario-telco-1.0.0.jar 

