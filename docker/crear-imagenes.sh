#!/bin/bash
docker build -t gft-db-h2:v1 -f Dockerfile-h2 .
docker build -t config-server:v1 -f Dockerfile-config-server .
docker build -t eureka-server:v1 -f Dockerfile-eureka-server .
docker build -t zuul-server:v1 -f Dockerfile-zuul-server .
docker build -t microservice-dtb:v1 -f Dockerfile-microservice-dtb .
docker build -t microservice-clientes-cuentas:v1 -f Dockerfile-microservice-clientes-cuentas .