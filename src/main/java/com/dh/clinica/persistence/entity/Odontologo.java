package com.dh.clinica.persistence.entity;
import com.dh.clinica.dto.OdontologoDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "odontologos")
public class Odontologo {

    @Id
    @SequenceGenerator(name = "odontologo sequence", sequenceName = "odontologo_sequence")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "odontologo_sequence")
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer matricula;
    @OneToMany(mappedBy = "odontologo", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Turno> turnos= new HashSet<>();


    public Odontologo() {}

    public Odontologo(Integer id) {
        this.id = id;
    }

    public Odontologo(OdontologoDto odontologo) {
        this.nombre = odontologo.getNombre();
        this.apellido = odontologo.getApellido();
        this.matricula = odontologo.getMatricula();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "Odontologo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", matricula=" + matricula +
                '}';
    }
}
