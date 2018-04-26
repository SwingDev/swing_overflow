FROM java:8
WORKDIR /swing_overflow
ADD ./build/libs/swing_overflow-0.0.1-SNAPSHOT.jar /swing_overflow/swing_overflow.jar
CMD java -jar swing_overflow.jar