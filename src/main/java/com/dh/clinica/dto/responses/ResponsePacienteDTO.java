package com.dh.clinica.dto.responses;

import com.dh.clinica.persistence.entity.Paciente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponsePacienteDTO {
    private String nombre;
    private String apellido;

    public ResponsePacienteDTO(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }
}
