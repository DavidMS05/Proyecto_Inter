@echo off

REM Create JavaDoc directory
mkdir JavaDoc

REM Generate JavaDocs for clases and db packages
javadoc -d JavaDoc -sourcepath ByteScore clases db

cmd /k