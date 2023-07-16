@echo off
echo "build jar"
call mvn clean package -Dmaven.test.skip=true

echo "build docker image"
docker build -t porn:latest .

echo "export docker image"
docker save -o porn.tar porn:latest

echo "uploading"
scp porn.tar admin@192.168.0.109:/var/services/homes/admin

pause