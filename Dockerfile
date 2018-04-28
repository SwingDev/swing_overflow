FROM java:8
WORKDIR /swing_overflow
ADD . /swing_overflow
RUN ./gradlew --info --stacktrace -x test build

CMD exec java ${JAVA_OPTS} -jar ./build/libs/swing_overflow-0.0.1-SNAPSHOT.jar