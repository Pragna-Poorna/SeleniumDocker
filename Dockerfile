FROM bellsoft/liberica-openjdk-alpine:17

# Install curl and jq
RUN apk add curl jq

# workspace
WORKDIR /home/selenium-docker

# Add the required files
ADD target/docker-resources ./
ADD runner.sh runner.sh

RUN dos2unix runner.sh

# Run the tests
ENTRYPOINT sh runner.sh