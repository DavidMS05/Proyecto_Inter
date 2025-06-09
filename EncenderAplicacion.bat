@echo off

REM Create bin directory for compiled classes
mkdir ByteScore\bin

REM Compile all .java files and preserve package structure
javac -cp "ByteScore\lib\mysql-connector-java-8.0.23.jar" -d ByteScore\bin ByteScore\db\*.java ByteScore\clases\*.java

REM Run the main class
java -cp "ByteScore\bin;ByteScore\lib\mysql-connector-java-8.0.23.jar" clases.Main

cmd /k