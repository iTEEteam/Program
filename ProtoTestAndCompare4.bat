@echo off
title "Proto Test & Compare 4"

echo "Deleting last release (bin dir)..."
if exist bin rmdir bin /s /q

mkdir bin
echo "Compiling Proto..."
javac -d ./bin/ proto/*.java
echo "Proto compiled succesfully."

echo "Compiling TxtComparer..."
javac -d ./bin/ txtComparer/TxtComparer.java
echo "TxtComparer compiled succesfully."

echo "Running Proto with test4.txt..."
java -classpath .\bin\ proto.ProtoTester < test4.txt > test4.out

echo "Comparing output with expected output..."
java -classpath .\bin\ txtComparer.TxtComparer test4.out test4.expected


pause