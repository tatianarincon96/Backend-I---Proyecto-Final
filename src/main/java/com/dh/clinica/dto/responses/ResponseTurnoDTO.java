package com.dh.clinica.dto.responses;
import com.dh.clinica.persistence.entity.Turno;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ResponseTurnoDTO {
    private Integer id;
    private ResponsePacienteDTO paciente;
    private ResponseOdontologoDTO odontologo;
    private LocalDate fecha;
    private String hora;

    public ResponseTurnoDTO(Turno turno) {
        this.id = turno.getId();
        this.fecha = turno.getFecha();
        this.hora = turno.getHora();
    }
}
