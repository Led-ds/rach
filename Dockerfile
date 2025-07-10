FROM ubuntu:latest
LABEL authors="led-d"

ENTRYPOINT ["top", "-b"]