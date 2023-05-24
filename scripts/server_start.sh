#!/usr/bin/env bash
PROJECT_ROOT="/home/ubuntu/build"
JAR="$PROJECT_ROOT/IdeaArchieve.jar"

APP_LOG="$PROJECT_ROOT/application.log"
ERROR_LOG="$PROJECT_ROOT/error.log"
START_LOG="$PROJECT_ROOT/start.log"
ENV_LOG = "$PROJECT_ROOT/env.log"

NOW=$(date +%c)

echo "[$NOW] $JAR 복사" >> $START_LOG
cp $PROJECT_ROOT/build/libs/IdeaArchieve-0.0.1-SNAPSHOT.jar $JAR

echo "[$NOW] > $JAR 실행" >> $START_LOG
cd $PROJECT_ROOT/
echo "${{ secrets.IA_BACKEND_ENV }}" > /src/main/resources/application.yml 
nohup java -jar IdeaArchieve.jar > $APP_LOG 2> $ERROR_LOG &

SERVICE_PID=$(pgrep -f $JAR)
echo "[$NOW] > 서비스 PID: $SERVICE_PID" >> $START_LOG
