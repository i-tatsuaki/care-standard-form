#!/bin/sh

# 実行されているアプリケーションを停止する
ps aux | grep "java -jar care-standard-form-1.0-SNAPSHOT.jar" | grep -v grep | awk '{ print "kill", $2 }' | sh

