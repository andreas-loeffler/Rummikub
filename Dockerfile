FROM hseeberger/scala-sbt:8u265_1.4.3_2.13.4
WORKDIR /rummikub
ADD . /rummikub
CMD sbt run