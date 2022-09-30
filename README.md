# RESTful Biblioteca

### Desarrollo de una API de servicios web RESTful
Se desarrolla 2 API Rest para dar a conocer los datos de libros y biblioteca, mediante Spring Boot
estos servicios web RESTful realizaran operaciones CRUD con MySQL.


### Relacion Bidireccional
Tanto de "libros" voy a poder acceder/obtener datos a "biblioteca" y vicebersa.

@OneToMany: Ya que una biblioteca puede tener muchos libros.

@ManyToOne: Muchos libros pueden pertenecerle a una biblioteca.

Imagen de la relación a realizar en BD:

![Image text](https://github.com/yadevom/apirest_biblioteca_bidirecional/blob/main/relacion_bd.png)

### Dependencias utilizadas para App
- MySQL Drive
- Spring Data JPA
- Spring Web
- Spring Boot DevTools
- Validation

### Creación Base de Dato (MySQL)
Debemos tener creada la BD antes de ejecutar la App
```
create database colegio_editorial;
```
### Configuración conexión BD
En el archivio application.properties, indicar el nombre de la BD, username y password:
```
spring.datasource.url = jdbc:mysql://localhost:3306/colegio_editorial 
spring.datasource.username = $USER
spring.datasource.password = $PASS
```
### Script SQL
Consulta de bibliotecas:
```
SELECT * FROM colegio_editorial.library;
```

Consulta de libros:
```
SELECT * FROM colegio_editorial.books ;
```

### Consultas end point Biblioteca
Consulta todas las bibliotecas - GET ```http://localhost:8080/api/library```

Consulta biblioteca especifica - GET ```http://localhost:8080/api/library/2```

Eliminar biblioteca - DELETE ```http://localhost:8080/api/library/2```

Registrar biblioteca - POST ```http://localhost:8080/api/library```
```
{
    "name": "Casa de programacion"
}
```
Actualizar biblioteca - PUT ```http://localhost:8080/api/library/2```

Indicar el ID actualizar y escribir cuerpo dato a modificar
```
{
    "name": "Arquitectura Frontend"
}
```

### Consultas end point Libros
Registrar un libro - POST ```http://localhost:8080/api/books```
```
{
    "name": "ReactJS Ganchos",
    "library": {
        "id" : 2
    }
}
```

Consulta todas los libros - GET ```http://localhost:8080/api/books```

Consulta libro en especifico - GET ```http://localhost:8080/api/books/2```

Eliminar libro - DELETE ```http://localhost:8080/api/books/2```


Actualizar libro - PUT ```http://localhost:8080/api/books/1```

Indicar el ID actualizar dato del libro o biblioteca a modificar
```
{
    "name": "Algoritmia avanzada",
    "library": {
        "id" : 1
    }
}
```

