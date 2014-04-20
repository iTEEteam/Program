@echo off
title "Proto Test & Compare 3"

echo "Deleting last release (bin dir)..."
if exist bin rmdir bin /s /q

mkdir bin
echo "Compiling Proto..."
javac -d ./bin/ proto/*.java
echo "Proto compiled succesfully."

echo "Compiling TxtComparer..."
javac -d ./bin/ txtComparer/TxtComparer.java
echo "TxtComparer compiled succesfully."

echo "Running Proto with test3.txt..."
java -classpath .\bin\ proto.ProtoTester < test3.txt > test3.out

echo "Comparing output with expected output..."
java -classpath .\bin\ txtComparer.TxtComparer test3.out test3_expected.out


pause