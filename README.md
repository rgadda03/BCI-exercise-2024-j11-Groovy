# BCI-exercise-2024-j11-Groovy

### To run the application you would need:
- **openjdk** version 11
- **Gradle** 7.4
- **Postman**

### POSTMAN COLLECTION
- [Postman Collection](./Ejercicio%201.postman_collection.json)

### DIAGRAMS
- [Sequence Diagram sign-up and log-in and Component diagram](./diagramas/diagrama secuencia bci 2024.jpg)
- [Component Diagram](./diagramas/diagrama de componentes bci 2024.jpg)

### API DOCUMENTATION
#### **[POST]** <base-url>/sign-up
- **body:** 
```json
{
    "name": "John Doe",
    "email": "john.doe2@example.com",
    "password": "secuPas12",
    "phones": [
        {
            "number": "123456789",
            "citycode": "123",
            "countrycode": "10"
        }
    ]
}

response:
```json
{
    "id": "77ff64ad-5af9-44fc-82ba-8bd375f35922",
    "created": "2025-02-03",
    "lastLogin": null,
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huLmRvZTJAZXhhbXBsZS5jb20iLCJleHAiOjE3Mzg4MjcxNjAsImlhdCI6MTczODYxMTE2MH0.rt-KeL_LHSSEA2Fa62Xshwm7XC_-0nxnAxYTz8vAZWzs6P6F_o4E2zgXePz7f31IrqfaPccGE1q-JWHWsqTrtA",
    "isActive": true
}
 

[POST] <base-url>/login?token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huLmRvZTJAZXhhbXBsZS5jb20iLCJleHAiOjE3Mzg4MjcxNjAsImlhdCI6MTczODYxMTE2MH0.rt-KeL_LHSSEA2Fa62Xshwm7XC_-0nxnAxYTz8vAZWzs6P6F_o4E2zgXePz7f31IrqfaPccGE1q-JWHWsqTrtA
body:
{}
 

response: 
```json
Respuesta correcta:
{
    "id": "77ff64ad-5af9-44fc-82ba-8bd375f35922",
    "created": "2025-02-03",
    "lastLogin": "2025-02-03",
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huLmRvZTJAZXhhbXBsZS5jb20iLCJleHAiOjE3Mzg4MjcyMjEsImlhdCI6MTczODYxMTIyMX0.egZ_UWqYzaHFuHfsP0CX5M2Ahhy2NFTONkOsxn7B2YZvQkqn5tL3qBDS_PHsSpOomsne1qXQQldDScVn4uCY_Q",
    "name": "John Doe",
    "email": "john.doe2@example.com",
    "password": "secuPas12",
    "phones": [],
    "isActive": true
}

Respuesta con jwt vencido:
´´´json
{
    "error": [
        {
            "timestamp": "2025-02-03T19:34:07.229+00:00",
            "codigo": 0,
            "detail": "error general: JWT expired at 2024-12-26T05:09:32Z. Current time: 2025-02-03T16:34:07Z, a difference of 3410675229 milliseconds.  Allowed clock skew: 0 milliseconds."
        }
    ]
}

Configurations:
You dont need any configuration, the database is set on H2.
The JWT token is provided by the signUp operation.