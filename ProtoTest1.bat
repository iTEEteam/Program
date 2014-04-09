@echo off
title Proto Test 1

ECHO Compiling Proto Test 1
javac proto/*.java
ECHO Compiled Proto Test 1
ECHO Running Proto Test 1
java proto.ProtoTester < test1.txt



pause