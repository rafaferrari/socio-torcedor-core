# socio-torcedor-core

Servico responsavel pelo gerenciamento de Socios Torcedores.

# Tecnologias utilizadas neste projeto

- SpringBoot Stack
- Java 8
- Netflix Hystrix
- Swagger
- JPA/Hibernate
- Servidor Jetty
- Testes: Mockito, Junit, Jmeter
- Gestor de Logs: Log4J
- Versionamento: Git
- Gradle

# Forma de Uso

- Clonar este repositorio
- Instalar [Gradle](https://gradle.org).
- Inicializar o servico
- API do Swagger: 
```bash
http://localhost:8081/swagger-ui.html
```
- Para rodar os testes, executar: "clean test jacocoTestReport"
- Caso seja necessario alterar a porta do servico, acessar o arquivo application.yml e alterar a propriedade abaixo:
```bash
server.port: 8081
```

# Arquitetura

- Desenvolvimento orientado a microservice
- Arquitetura e desenvolvimento de APIs REST
- Projeto com modelo em camadas

# About

Desenvolvido por Rafael Lopes Ferrari
