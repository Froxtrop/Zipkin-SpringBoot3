# Zipkin-SpringBoot3

## Requests
### Crear registro POST (este es el unico que se comunica con el servicio 2)

http://localhost:8080/user/create
```
{
    "usuario": "auditoriaZipkin7",
    "pass": "123",
    "apellido": "postmanZ7",
    "nombre": "testzipki7"
}
```

### Obtener registro GET

http://localhost:8080/user/get/{usuario}

### Actualizar registro PUT

http://localhost:8080/user/update
```
{
    "id": 1
    "usuario": "auditoriaZipkin7",
    "pass": "123",
    "apellido": "nombre",
    "nombre": "apellido"
}
```
### Eliminar registro DELETE

http://localhost:8080/user/delete/{usuario}
