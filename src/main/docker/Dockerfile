FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
RUN sh -c 'touch /Pricerus-PriceFinder-rest-service-0.1.0.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
