package com.dh.clinica.dto;
import com.dh.clinica.persistence.entity.Odontologo;
import com.dh.clinica.persistence.entity.Paciente;
import com.dh.clinica.persistence.entity.Turno;
import java.time.LocalDate;

public class TurnoDto {
    private Integer id;
    private PacienteDto paciente;
    private OdontologoDto odontologo;
    private LocalDate fecha;
    private String hora;

    public TurnoDto() {}

    public TurnoDto(Integer id, PacienteDto paciente, OdontologoDto odontologo, LocalDate fecha, String hora) {
        this.id = id;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fecha = fecha;
        this.hora = hora;
    }

    public TurnoDto(Turno t) {
        this.id = t.getId();
        this.paciente = new PacienteDto(t.getPaciente().getId());
        this.odontologo = new OdontologoDto(t.getOdontologo().getId());
        this.fecha = t.getFecha();
        this.hora = t.getHora();
    }

    public PacienteDto getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteDto paciente) {
        this.paciente = paciente;
    }

    public OdontologoDto getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(OdontologoDto odontologo) {
        this.odontologo = odontologo;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Turno toEntity(Paciente paciente, Odontologo odontologo, LocalDate fecha, String hora) {
        Turno entity = new Turno();

        entity.setId(id);
        entity.setPaciente(paciente);
        entity.setOdontologo(odontologo);
        entity.setFecha(fecha);
        entity.setHora(hora);

        return entity;
    }
}
