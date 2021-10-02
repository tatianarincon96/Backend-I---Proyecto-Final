# BackEnd I - Proyecto Final Clínica Odontológica
## Sinopsis del proyecto
##### Software de una clínica odontólogica en la que se pueden agregar, buscar, modificar y eliminar pacientes, odontólogos y turnos mediante peticiones a una API REST
## Tecnologías utilizadas
- ***JAVA***
- ***Maven***
- ***Spring Boot***

## Endpoints
### Odontólogos

- Buscar por id: GET a PATH/odontologos/{id}

  * 200 OK → devuelve el odontólogo
  * 404 NOT FOUND → No se encontró un odontólogo con el id ingresado

- Registrar nuevo: POST a PATH/odontologos/nuevo

  * 200 OK → Devuelve el odontólogo
  ```RequestBody:
  {
      "nombre": "Lorena",
      "apellido": "Gutierrez",
      "matricula": 123452
  }
  ```
  
- Actualizar existente: PUT a PATH/odontologos

  * 200 OK → Devuelve el odontólogo
  * 404 NOT FOUND → No se encontró un odontólogo con el id ingresado
  ```RequestBody:
  {
     "id": "1",
     "nombre": "Lorena",
     "apellido": "Gutierrez",
     "matricula": 123452
  }
  ```

- Eliminar por id: DELETE a PATH/odontologos/{id}

  * 200 OK → Se elimino el odontólogo correctamente
  * 404 NOT FOUND → No se encontró un odontólogo con el id ingresado

- Obtener todos: GET a PATH/odontologos/todos

  * 204 NO CONTENT → la lista está vacía


### Pacientes
- Buscar por id: GET a PATH/pacientes/{id}

  * 200 OK → devuelve el paciente
  * 404 NOT FOUND → No se encontró un paciente con el id ingresado

- Registrar nuevo: POST a PATH/pacientes/nuevo

  * 200 OK → Devuelve el paciente
  ```RequestBody:
  {
    "nombre": "Julieta",
    "apellido": "Leti",
    "dni": "234567864",
    "domicilio": {
        "calle": "Lombardi",
        "numero": "345",
        "localidad": "Lujan",
        "provincia": "Buenos Aires"
    }
  }
  ```
  
- Actualizar existente: PUT a PATH/pacientes

  * 200 OK → Devuelve el paciente
  * 404 NOT FOUND → No se encontró un paciente con el id ingresado
  ```RequestBody:
  {
    "id": 1,
    "nombre": "Julieta",
    "apellido": "Leti",
    "dni": "234567864",
    "domicilio": {
        "calle": "Lombardi",
        "numero": "345",
        "localidad": "Lujan",
        "provincia": "Buenos Aires"
    }
  }
  ```

- Eliminar por id: DELETE a PATH/pacientes/{id}

  * 200 OK → Se elimino el paciente correctamente
  * 404 NOT FOUND → No se encontró un paciente con el id ingresado

- Obtener todos: GET a PATH/pacientes/todos
  * 204 NO CONTENT → la lista está vacía


## Turnos:

- Buscar por id: GET a PATH/turnos/{id}

  * 200 OK → devuelve el turno con el nombre y apellido de paciente y odontólogo
  * 404 NOT FOUND → No se encontró un turno con el id ingresado

- Registrar nuevo: POST a PATH/turnos/nuevo

  * 200 OK → devuelve el turno con el nombre y apellido de paciente y odontólogo
  ```RequestBody:
  {
    "paciente": {"id": "1"},
    "odontologo": {"id": "1"},
    "fecha": "2021-10-08"
    "hora": "15:30"
  }
  ```
  
- Actualizar existente: PUT a PATH/turnos

  * 200 OK → devuelve el turno con el nombre y apellido de paciente y odontólogo
  * 404 NOT FOUND → No se encontró un turno con el id ingresado
  ```RequestBody:
  {
    "id": 1,
    "paciente": {"id": "1"},
    "odontologo": {"id": "1"},
    "fecha": "2021-10-08"
    "hora": "15:30"
  }
  ```

- Eliminar por id: DELETE a PATH/turnos/{id}

  * 200 OK → Se elimino el turno correctamente
  * 404 NOT FOUND → No se encontró un turno con el id ingresado

- Obtener todos: GET a PATH/turnos/todos
  * 200 OK → devuelve todos los turnos
  * 204 NO CONTENT → la lista está vacía

- Obtener todos próxima semana: GET a PATH/turnos/proxima-semana
  * 200 OK → devuelve todos los turnos de los próximos 7 días
  * 204 NO CONTENT → la lista está vacía

## Proyecto desarrollado por Tatiana Rincon. Gracias.
