# Flight App Mindswap

Flight App Mindswap is a Rest API produced by Luis Faria, Tiago Correia, Martim Margarido, Tomás Fonseca, Vanilson Muhongo. This app was developed during the Mindswap Bootcamp as the first backend project.

You will be able to register your account and log in.
Once logged in (as user) you'll be able to search, book and cancel flights.
If given admin previleges, you'll be able to create and delete flights and also get the passenger list from a given flight.

It's expected you'll receive confirmation emails of your interactions with the app.

### App URL Heroku
https://mindswap-flight-app.herokuapp.com/

### Documentation (Swagger)
https://mindswap-flight-app.herokuapp.com/swagger-ui/index.html

### Postman collections
#### At the root of this project you have the postman collections to use the app locally or online:<br/>
-Heroku-app.postman_collection.json<br/>
-LocalHost.postman_collection.json<br/>

### Docker and Docker Composed (Localhost)

#### TO run the DB
docker run --name mymariadb -e MARIADB_ROOT_PASSWORD=mypass -p 3306:3306 -d mariadb:latest

#### TO build the APP
docker build -t users_service . 

#### TO run the APP
docker run -it --rm --link mymariadb  -p 8080:8080 users_service

#### TO run the APP in compose
docker-compose up



