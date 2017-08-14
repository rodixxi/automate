cd QCAFrameworks\QCAFrameworks-master\QCAFrameworkCorePOM
call mvn -B clean compile -DskipTests
call mvn -B clean install -DskipTests
echo =========== CorePOM OK! ===========
pause
cd ..\QCAFrameworkCore
call mvn -B clean compile -DskipTests
call mvn -B clean install -DskipTests
echo =========== Core OK! ===========
pause
cd ..\QCAFrameworkWindowsWeb
call mvn -B clean compile -DskipTests
call mvn -B clean install -DskipTests
echo =========== WindowsWeb OK! ===========
pause
cd ..\..\..\Gestar
call mvn -B clean compile -DskipTests
call mvn -B clean install -DskipTests
echo =========== Gestar OK! ===========
echo =========== Todo OK! ===========
pause