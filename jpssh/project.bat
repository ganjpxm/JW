set path=D:\program\green\softwareDevelop\other\ant\v1.8.2\bin;%path%
cd D:\program\green\softwareDevelop\ide\eclipse\sts\workspace2\jpw
@echo  build.xml package-war gencode.xml gen-crud build.xml xdoclet-tag
ant -f build.xml package-war

pause
ECHO success
