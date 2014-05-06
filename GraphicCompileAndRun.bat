@REM 
@REM         IIIIIIII              ******       ******
@REM         IIIIIIII            **********   **********
@REM         IIIIIIII          ************* *************
@REM         IIIIIIII         *****************************
@REM         IIIIIIII         *****************************
@REM         IIIIIIII         *****************************
@REM         IIIIIIII          ***************************
@REM         IIIIIIII            ***********************
@REM         IIIIIIII              *******************
@REM         IIIIIIII                ***************
@REM         IIIIIIII                  ***********
@REM         IIIIIIII                    *******
@REM         IIIIIIII                      ***
@REM         IIIIIIII                       *
@REM 
@REM                Szoftver Laboratórium 4.

@echo off
title "Graphic Test"

echo "Deleting last release (bin dir)..."
if exist bin rmdir bin /s /q

mkdir bin
echo "Compiling Graph..."
javac -d ./bin/ graph/*.java
echo "Graph compiled succesfully."

echo "Running Graph..."
java -classpath .\bin\ graph.Application
