package com.dh.clinica.dto;

import com.dh.clinica.persistence.entity.Odontologo;

public class OdontologoDto {

    private Integer id;
    private String nombre;
    private String apellido;
    private Integer matricula;

    public OdontologoDto() {}

    public OdontologoDto(Odontologo odontologo ) {
        this.id = odontologo.getId();
        this.nombre = odontologo.getNombre();
        this.apellido = odontologo.getApellido();
        this.matricula = odontologo.getMatricula();
    }

    public OdontologoDto(Integer id, String nombre, String apellido, Integer matricula) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }

    public OdontologoDto(Integer id) {
        this.id = id;
    }

    public OdontologoDto(String nombre, String apellido, Integer matricula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
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

    public Odontologo toEntity() {
        Odontologo entity = new Odontologo();

        entity.setId(id);
        entity.setNombre(nombre);
        entity.setApellido(apellido);
        entity.setMatricula(matricula);

        return entity;
    }
}
