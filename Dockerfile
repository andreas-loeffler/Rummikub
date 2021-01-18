FROM hseeberger/scala-sbt:latest
WORKDIR /rummikub
ADD . /rummikub
CMD sbt test