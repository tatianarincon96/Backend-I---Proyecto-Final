package com.dh.clinica.dto.responses;

import com.dh.clinica.persistence.entity.users.User;
import com.dh.clinica.persistence.entity.users.UserRoles;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseUserDTO {
    private Integer id;
    private String nombre;
    private String username;
    private String email;
    private UserRoles userRoles;

    public ResponseUserDTO(User user) {
        this.id = user.getId();
        this.nombre = user.getNombre();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.userRoles = user.getUserRoles();
    }
}
