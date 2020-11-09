# Innovate Apps - Restaurant

### Descripción
La api Restaurant es un ejemplo practico que realiza crud de tablas: 
Restaurante, ambientes y comidas. El mismo lo realiza en SpringBoot para el backend.
La api tiene varios perfiles de compilación, para la conexion de base de datos:
dev: localhost
hk: heroku
prod: Produccion
### Requerimientos
La siguiente api necesita:

* java 11
* Apache Maven 3.6.0

### Librerias
Las librerias para la realizacion de la api, son las siguientes:

* [springframework](https://spring.io/guides/gs/rest-service/)
* [mysql-connector-java](https://mvnrepository.com/artifact/mysql/mysql-connector-java)
* [lombok](https://mvnrepository.com/artifact/org.projectlombok/lombok)
* [mapstruct](https://mvnrepository.com/artifact/org.mapstruct/mapstruct)
* [swagger2](https://mvnrepository.com/artifact/io.springfox/springfox-swagger2)
* [swagger-ui](https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui)
* [jsonwebtoken](https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt)


## Uso
The http-request library is available from [Maven Central](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.github.kevinsawicki%22%20AND%20a%3A%22http-request%22).

```xml
<dependency>
  <groupId>com.github.javiercode</groupId>
  <artifactId>innovateapp-restaurant</artifactId>
  <version>1.0</version>
</dependency>
```

# Usando el complemento descargado

```
$ cd innovateapp-restaurant
$ mvn clean install
```

### Ejemplos
La api se encuentra publicada y documentada en:
[Heroku](https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt)https://innovateapps-restaurant.herokuapp.com/innovate-apps/api/swagger-ui.html)


### Seguridad
La Api tambien esta implementada con seguridad JSONToken la misma se la puede ver en la rama seguridad.