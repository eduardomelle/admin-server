## Instalação

pom.xml:

```bash
    <dependency>
	    <groupId>de.codecentric</groupId>
	    <artifactId>spring-boot-admin-starter-client</artifactId>
    </dependency>
```

application.properties:

```bash
    # Spring Boot Admin Client
    spring.boot.admin.client.url=http://localhost:8080/admin-server
    spring.boot.admin.client.instance.service-base-url=http://localhost:8081
    spring.boot.admin.client.username=admin
    spring.boot.admin.client.password=admin
    spring.boot.admin.client.instance.metadata.user.name=admin
    spring.boot.admin.client.instance.metadata.user.password=admin
```