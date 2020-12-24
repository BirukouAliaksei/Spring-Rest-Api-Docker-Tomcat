# Spring-Rest-Api-Docker-Tomcat

##DESCRIPTION

**Test project. Scooter service api.  
technologies: Spring MVC, Spring Security(JWT token), Hibernate, Tomcat, MySql, Docker, Swagger.  
Rest: You can find rest api templates in description.txt.  
Tomcat port: 8088.  
MySql port: 3306.  

##QUICK START

##Tomcat start  
build project: mvn clean package instal.  
start project: tomcat7: run-war.  

##Docker start:
go to Config module - resources - application.properties.  
change uncomment properties for docker, and comment properties for tomcat.  
build project: mvn clean package instal.  
model for start docker:
docker image/container/network ls - просмотр созданых обрахов или контейнеров или сетей.  
docker image build -t mysql-image .- создание образа, после -t идет название образа.  
docker container run -d --net scooter-network -v mysql-storage:/var/lib/mysql --name dockermysqldocker mysql-image-  
запуск контейнера --name dockermysqldocker это разсположение хоста, можно настроить в aplication.properties.  
docker container exec -it CONTAINER_ID/NAME bash - зайти в контейнер.  
mysql -uroot -p < scooterDbFile.sql - вызов пароля и подтягиваниее базы.  
docker container run -p 8088:8080 --net scooter-network --name scooterservice scooter-service-image.  
-p 8088 - удаленный хост, хост докера.  
Used this site for start:  
https://medium.com/@hellosebinvincent/dockerizing-spring-boot-application-with-persistent-mysql-database-2ad66ef0c6  

-
