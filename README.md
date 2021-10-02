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
  * 404 NOT FOUND → No existe un odontólogo con el id ingresado

- Registrar nuevo: POST a PATH/odontologos/nuevo

  * 200 OK → Devuelve el odontólogo
  ```
  {
      "nombre": "Lorena",
      "apellido": "Gutierrez",
      "matricula": 123452
  }
  ```
  
- Actualizar existente: PUT a PATH/odontologos

  * 200 OK → Devuelve el odontólogo
  * 404 NOT FOUND → No existe un odontólogo con el id ingresado
  ```
  {
     "id": "1",
     "nombre": "Lorena",
     "apellido": "Gutierrez",
     "matricula": 123452
  }
  ```

- Eliminar por id: DELETE a PATH/odontologos/{id}

  * 200 OK → Odontólogo eliminado
  * 404 NOT FOUND → No existe un odontólogo con el id ingresado

- Obtener todos: GET a PATH/odontologos/todos
  * 200 OK → Lista de odontólogos
  * 204 NO CONTENT → la lista está vacía


### Pacientes
- Buscar por id: GET a PATH/pacientes/{id}

  * 200 OK → devuelve el paciente
  * 404 NOT FOUND → No existe un paciente con el id ingresado

- Registrar nuevo: POST a PATH/pacientes/nuevo

  * 200 OK → Devuelve el paciente
  ```
  {
    "nombre": "Lucas",
    "apellido": "Garcia",
    "dni": "12343598",
    "fechaIngreso": "2021-03-05",
    "domicilio": {
        "calle": "Garibaldi",
        "numero": "1254",
        "localidad": "Lomas de Zamora",
        "provincia": "Buenos Aires"
    }
  }
  ```
  
- Actualizar existente: PUT a PATH/pacientes

  * 200 OK → Devuelve el paciente
  * 404 NOT FOUND → No existe un paciente con el id ingresado
  ```
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

  * 200 OK → Paciente eliminado
  * 404 NOT FOUND → No existe un paciente con el id ingresado

- Obtener todos: GET a PATH/pacientes/todos
  * 200 OK → Lista de pacientes
  * 204 NO CONTENT → la lista está vacía


### Turnos

- Buscar por id: GET a PATH/turnos/{id}

  * 200 OK → devuelve el turno con el nombre y apellido de paciente y odontólogo
  * 404 NOT FOUND → No existe un turno con el id ingresado

- Registrar nuevo: POST a PATH/turnos/nuevo

  * 200 OK → devuelve el turno con el nombre y apellido de paciente y odontólogo
  * 400 BAD REQUEST → La hora y fecha solicitada no está disponible (En caso que se intente ingresar otro turno en la misma fecha y hora)
  ```
  {
    "paciente": {"id": "1"},
    "odontologo": {"id": "1"},
    "fecha": "2021-10-08"
    "hora": "15:30"
  }
  ```
  
- Actualizar existente: PUT a PATH/turnos

  * 200 OK → devuelve el turno con el nombre y apellido de paciente y odontólogo
  * 404 NOT FOUND → No existe un turno con el id ingresado
  ```
  {
    "id": 1,
    "paciente": {"id": "1"},
    "odontologo": {"id": "1"},
    "fecha": "2021-10-08"
    "hora": "15:30"
  }
  ```

- Eliminar por id: DELETE a PATH/turnos/{id}

  * 200 OK → Turno eliminado
  * 404 NOT FOUND → No existe un turno con el id ingresado

- Obtener todos: GET a PATH/turnos/todos
  * 200 OK → devuelve todos los turnos
  * 204 NO CONTENT → la lista está vacía

- Obtener todos próxima semana: GET a PATH/turnos/proxima-semana
  * 200 OK → devuelve todos los turnos de los próximos 7 días
  * 204 NO CONTENT → la lista está vacía
