@echo off
title "Proto Test & Compare 2"

echo "Deleting last release (bin dir)..."
if exist bin rmdir bin /s /q

mkdir bin
echo "Compiling Proto..."
javac -d ./bin/ proto/*.java
echo "Proto compiled succesfully."

echo "Compiling TxtComparer..."
javac -d ./bin/ txtComparer/TxtComparer.java
echo "TxtComparer compiled succesfully."

echo "Running Proto with test2.txt..."
java -classpath .\bin\ proto.ProtoTester < test2.txt > test2.out

echo "Comparing output with expected output..."
java -classpath .\bin\ txtComparer.TxtComparer test2.out test2.expected


pause