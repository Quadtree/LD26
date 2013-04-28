call mvn clean
call mvn install
cd html
call mvn clean
call mvn package
cd ..
rem cp -v html/target/planetfall-html-1.0-SNAPSHOT/* U:/detour
cp -Rv html/target/planetfall-html-1.0-SNAPSHOT/planetfall U:/detour