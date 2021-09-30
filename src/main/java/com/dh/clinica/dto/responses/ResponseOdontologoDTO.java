package com.dh.clinica.dto.responses;

import com.dh.clinica.persistence.entity.Odontologo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseOdontologoDTO {
    private String nombre;
    private String apellido;

    public ResponseOdontologoDTO(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }
}
