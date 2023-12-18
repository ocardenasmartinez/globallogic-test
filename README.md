# GlobalLogic Test

## Projecto de prueba para GlobalLogic

### Descarga del proyecto;

    git clone https://github.com/ocardenasmartinez/globallogic-test.git

### Compilación del proyecto

### En la raiz del proyecto 

    Para linux o mac
    ./gradlew clean build
    Para Windows
    gradlew.bat clean build

### Correr el proyecto

    Para linux o mac
    ./gradlew bootRun
    Para Windows
    gradlew.bat bootRun

### Crear usuario

    curl --header "Content-Type: application/json" --request POST --data '{"name": "Juan", "email": "correo@email.com", "password": "a2asfGfdfdf4", "phones": [{"number": "123456", "cityCode": "1", "countryCode": "CL"}]}' http://localhost:8080/signup

### Respuesta de la creación de usuario

    {
        "id": "54080e11-ce12-4d61-abfd-45ee9ed99f20",
        "created": "2023-12-15T23:42:31.353+00:00",
        "lastLogin": "2023-12-15T23:42:31.353+00:00",
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKdWFuIiwiZXhwIjoxNzAyNzE5NzUxLCJpYXQiOjE3MDI2ODM3NTF9.CfEaZcdY6QceRbTj9HSI4SmwJbXP_mgU7pYuZYVJ3ck",
        "active": true
    }

### Con el atributo token se consulta el usuario

### Consulta usuario

    curl -i http://localhost:8080/login -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKdWFuIiwiZXhwIjoxNzAyNzE5NzUxLCJpYXQiOjE3MDI2ODM3NTF9.CfEaZcdY6QceRbTj9HSI4SmwJbXP_mgU7pYuZYVJ3ck"




