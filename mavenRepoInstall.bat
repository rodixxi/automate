cd QCAFrameworks\QCAFrameworks-master\QCAFrameworkCorePOM
call mvn -B clean compile -DskipTests
call mvn -B clean install -DskipTests
echo =========== CorePOM OK! ===========
TIMEOUT /T 3
cd ..\QCAFrameworkCore
call mvn -B clean compile -DskipTests
call mvn -B clean install -DskipTests
echo =========== Core OK! ===========
TIMEOUT /T 3
cd ..\QCAFrameworkWindowsWeb
call mvn -B clean compile -DskipTests
call mvn -B clean install -DskipTests
echo =========== WindowsWeb OK! ===========
TIMEOUT /T 3
cd ..\..\..\Gestar
call mvn -B clean compile -DskipTests
call mvn -B clean install -DskipTests
echo =========== Gestar OK! ===========
echo =========== Todo OK! ===========
TIMEOUT /T 5