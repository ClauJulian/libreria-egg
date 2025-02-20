# Proyecto Libreria

## Descripción
Este proyecto es un sistema de gestión de una librería que permite administrar libros, editoriales y autores a través de una base de datos MySQL. Ofrece funcionalidades para la creación, consulta, modificación y eliminación de registros.

## Características
- Gestión de **Libros**, **Editoriales** y **Autores** en una base de datos MySQL.
- Operaciones **CRUD** (Crear, Leer, Actualizar, Eliminar) sobre las entidades del sistema.
- Implementación con **Hibernate ORM** para la persistencia de datos.
- Uso de **Jakarta Persistence API** para facilitar la gestión de entidades.
- Conexión con **MySQL** mediante el conector correspondiente.

## Tecnologías Utilizadas
- **Java 17**
- **Maven**
- **Hibernate ORM 6.4.4.Final**
- **Jakarta Persistence API 3.1.0**
- **MySQL Connector 9.2.0**
- **JUnit 5.9.2** (para pruebas)

## Instalación y Configuración
1. Clonar el repositorio del proyecto:
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   ```
2. Configurar la base de datos MySQL:
   - Crear una base de datos llamada `libreria`.
   - Configurar las credenciales en el archivo de configuración.
3. Construir el proyecto con Maven:
   ```bash
   mvn clean install
   ```
4. Ejecutar la aplicación:
   ```bash
   mvn exec:java
   ```

## Dependencias (pom.xml)
```xml
<project>
    <artifactId>libreria</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>5.9.2</version>
            <scope>test</scope>
        </dependency>
        
        <!-- Hibernate ORM -->
        <dependency>
            <groupId>org.hibernate.orm</groupId>
            <artifactId>hibernate-core</artifactId>
            <version>6.4.4.Final</version>
        </dependency>

        <!-- Jakarta Persistence API -->
        <dependency>
            <groupId>jakarta.persistence</groupId>
            <artifactId>jakarta.persistence-api</artifactId>
            <version>3.1.0</version>
        </dependency>

        <!-- MySQL Connector -->
        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <version>9.2.0</version>
        </dependency>
    </dependencies>
</project>
```

## Contacto
Para cualquier consulta o contribución, no dudes en contactarme.
Claudia Edith Julian
https://www.linkedin.com/in/claudiaedithjulian/
wg.claudiajulian@gmail.com

