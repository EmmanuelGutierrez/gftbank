# Evaluación técnica - gftbank

### Compilar los proyectos
Ejecutar los siguientes comandos:
```
$ cd componentes
$ gradle clean build -q copyJar projects
```
### Crear las imagenes de Docker
```
$ cd docker
$ ./crear-imagenes.sh
```
### Ejecutar los contenedores Docker
```
docker-compose up
```
### Swagger-ui

`http://localhost:8090/api/dtb/swagger-ui.html#/`

`http://localhost:8090/api/swagger-ui.html#/`