# Clinica API

Proyecto para gestionar citas medicas de pacientes dentro de una clinica, opreaciones soportadas (agendar, consultar, cancelar)

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Contributing](#contributing)
- [License](#license)

## Installation

    1. Ejecutar scripts de base de datos (Se comparten por correo).

        1.1. Ejecutar archivo user-bd.sql para crear usuario de base de datos.  

        1.2. Ejecutar archivo esquema-clinica-bd.sql para crear esquema y tablas.

    2. Clonar el repositorio git (ubicarse en rama master); git clone https://github.com/gitUserDiegoS/clinica-api.git.

    3. Importar a Ide de preferencia, descargar dependencias maven
    
 
 
Url base de api `http://localhost:8080`.

## Endpoints

1.Post - Registrar citas medicas

http://localhost:8080/api/citasmedicas

Request:

```json
{
    "cita":"Medicina General",
    "fecha": "2025-10-05T12:31:00",
    "estado": "Activa",
    "paciente": {        
        "nombre":"Diego",
        "apellido":"Sanchez Gomez"
    },
    "medico": {                
        "nombre":"Sara",
        "apellido":"Arbelaez"
    }
}
```

Response http 201: 
```json
Cita registrada con exito
```


2. Post - cancelar cita medica

http://localhost:8080/api/citasmedicas/cancelar

Request:

```json
{
    "citaId": 1,
    "pacienteId": 1
}
```

Response http 200:

```json
Cita cancelada con exito
```

3. Get - Consultar citas por medico

http://localhost:8080/api/medico/citasmedicas?id=1

Response:

```json
{
    "id": 1,
    "nombre": "Orm medi Postman",
    "apellido": "ig Postman",
    "citasMedicas": [
        {
            "id": 1,
            "cita": "Medicina general 1",
            "fecha": "2025-09-11T15:00:00.000+00:00",
            "estado": "Cancelada",
            "paciente": {
                "id": 1,
                "nombre": "diegogo oraldo Postman",
                "apellido": "SanchezY Postman"
            }
        },
        {
            "id": 4,
            "cita": "Medicina general 2",
            "fecha": "2025-09-11T12:00:00.000+00:00",
            "estado": "Activa",
            "paciente": {
                "id": 2,
                "nombre": "bra oraldo Postman",
                "apellido": "SanchezY Postman"
            }
        },
        {
            "id": 5,
            "cita": "Medicina general 2",
            "fecha": "2025-09-11T12:00:00.000+00:00",
            "estado": "Activa",
            "paciente": {
                "id": 2,
                "nombre": "bra oraldo Postman",
                "apellido": "SanchezY Postman"
            }
        },
        {
            "id": 3,
            "cita": "Medicina general 1",
            "fecha": "2025-09-11T12:00:00.000+00:00",
            "estado": "Activa",
            "paciente": {
                "id": 2,
                "nombre": "bra oraldo Postman",
                "apellido": "SanchezY Postman"
            }
        },
        {
            "id": 6,
            "cita": "Medicina general pasada",
            "fecha": "2024-12-05T14:15:00.000+00:00",
            "estado": "Cerrada",
            "paciente": {
                "id": 1,
                "nombre": "diegogo oraldo Postman",
                "apellido": "SanchezY Postman"
            }
        },
        {
            "id": 2,
            "cita": "Medicina general 1",
            "fecha": "2025-09-11T12:00:00.000+00:00",
            "estado": "Activa",
            "paciente": {
                "id": 1,
                "nombre": "diegogo oraldo Postman",
                "apellido": "SanchezY Postman"
            }
        }
    ]
}

```

4. Get - citas medicas futuras pacientes
http://localhost:8080/api/paciente/citasmedicasfuturas?id=1


Response:

```json
{
    "id": 1,
    "nombre": "diegogo oraldo Postman",
    "apellido": "SanchezY Postman",
    "citasMedicas": [
        {
            "id": 1,
            "cita": "Medicina general 1",
            "fecha": "2025-09-11T15:00:00.000+00:00",
            "estado": "Cancelada",
            "medico": {
                "id": 1,
                "nombre": "Orm medi Postman",
                "apellido": "ig Postman"
            }
        },
        {
            "id": 2,
            "cita": "Medicina general 1",
            "fecha": "2025-09-11T12:00:00.000+00:00",
            "estado": "Activa",
            "medico": {
                "id": 1,
                "nombre": "Orm medi Postman",
                "apellido": "ig Postman"
            }
        },
        {
            "id": 8,
            "cita": "Medicina general pasada",
            "fecha": "2025-11-05T08:15:00.000+00:00",
            "estado": "Cerrada",
            "medico": {
                "id": 2,
                "nombre": "cambia medico",
                "apellido": "ig Postman"
            }
        },
        {
            "id": 9,
            "cita": "Medicina general pasada",
            "fecha": "2025-11-05T10:15:00.000+00:00",
            "estado": "Activa",
            "medico": {
                "id": 2,
                "nombre": "cambia medico",
                "apellido": "ig Postman"
            }
        },
        {
            "id": 10,
            "cita": "Medicina general pasada",
            "fecha": "2025-10-05T10:15:00.000+00:00",
            "estado": "Activa",
            "medico": {
                "id": 2,
                "nombre": "cambia medico",
                "apellido": "ig Postman"
            }
        }
    ]
}
```
