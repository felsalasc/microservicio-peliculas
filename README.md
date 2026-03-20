Microservicio de Películas - Spring Boot
Descripción

Este proyecto corresponde a un microservicio desarrollado en Spring Boot que permite obtener información sobre películas mediante endpoints REST.

El sistema maneja los datos en memoria (sin base de datos) y expone servicios para consultar todas las películas o una en específico por su ID.

Tecnologías utilizadas

Java 17

Spring Boot 3

Maven

REST API

Estructura del proyecto
Peliculas/
├── src/
│   └── main/
│       ├── java/com/example/demo/
│       │   ├── controller/
│       │   ├── service/
│       │   ├── model/
│       │   └── PeliculasApplication.java
│       └── resources/
│           └── application.properties
├── pom.xml
 Cómo ejecutar el proyecto

Clonar el repositorio:

git clone https://github.com/felsalasc/microservicio-peliculas.git

Entrar al proyecto:

cd Peliculas

Ejecutar la aplicación:

./mvnw spring-boot:run

En Windows:

.\mvnw.cmd spring-boot:run

El servidor iniciará en:

http://localhost:8080
 Endpoints disponibles
 Obtener todas las películas
GET /peliculas

 Ejemplo:

http://localhost:8080/peliculas
Obtener película por ID
GET /peliculas/{id}

 Ejemplo:

http://localhost:8080/peliculas/1
 Ejemplo de respuesta
{
  "id": 1,
  "titulo": "Inception",
  "anio": 2010,
  "director": "Christopher Nolan",
  "genero": "Ciencia ficción",
  "sinopsis": "Un ladrón especializado en robar secretos a través de los sueños."
}
Características del sistema

Uso de arquitectura en capas (Controller, Service, Model)

Datos almacenados en memoria

Respuestas en formato JSON

Manejo de errores básicos (404 cuando no existe la película)

Requisitos

Java 17 o superior

Maven

Autor

Felipe Salas Cordero
