@echo off
title "Proto"

echo "Deleting last release (bin dir)..."
if exist bin rmdir bin /s /q

mkdir bin
echo "Compiling Proto..."
javac -d ./bin/ proto/*.java
echo "Proto compiled succesfully."

echo "Running Proto..."
cls
java -classpath .\bin\ proto.ProtoTester
pause
