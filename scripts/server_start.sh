#!/usr/bin/env bash
cd /home/ubuntu/build
chmod +x ./gradlew
./gradlew bootjar
java -jar build/libs/IdeaArchieve-0.0.1-SNAPSHOT.jar
