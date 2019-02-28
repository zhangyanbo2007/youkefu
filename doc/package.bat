cd UCKeFu-WebIM
call mvn clean
call mvn install
cd ..\UCKeFu-WeiXin
call mvn clean
call mvn install
cd ..\UCKeFu-WorkOrders
call mvn clean
call mvn install
cd ..\UCKeFu-XiaoE
call mvn clean
call mvn install
cd ..\UCKeFu-CallCenter
call mvn clean
call mvn install
cd ..\UCKeFu-All
call mvn clean
call mvn package
cd ..