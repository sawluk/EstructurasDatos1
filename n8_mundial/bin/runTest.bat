@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n8_mundial
REM Autor: Milena Vela - 10-may-2006
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Ejecucion de las pruebas
REM ---------------------------------------------------------

cd..

java -ea -classpath ./lib/mundial.jar;./test/lib/mundialTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.mundial.test.MundialTest
java -ea -classpath ./lib/mundial.jar;./test/lib/mundialTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.mundial.test.EquipoTest
java -ea -classpath ./lib/mundial.jar;./test/lib/mundialTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.mundial.test.JugadorTest

cd bin