# Evaluación técnica - gftbank

### Crear las imagenes de Docker

```
$ docker build -t bd-h2:v1 -f Dockerfile-h2 .
```


docker run --name gft-bd-h2 -p 1521:1521 -p 8082:8082 -d bd-h2:v1
