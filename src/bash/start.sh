#!/bin/bash
nohup java -jar NanoBot*.jar &
echo $! > NanoBot.pid
