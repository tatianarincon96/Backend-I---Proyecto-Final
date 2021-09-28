package com.dh.clinica.dto;
import com.dh.clinica.persistence.entity.users.User;
import com.dh.clinica.persistence.entity.users.UserRoles;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Integer id;
    private String nombre;
    private String username;
    private String email;
    private String password;
    private UserRoles userRoles;

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.nombre = user.getNombre();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.userRoles = user.getUserRoles();
    }

    public User toEntity() {
        User user = new User();
        user.setNombre(nombre);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setUserRoles(userRoles);
        return user;
    }
}
