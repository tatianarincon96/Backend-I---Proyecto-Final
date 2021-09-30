package com.dh.clinica.service.impl;
import com.dh.clinica.dto.responses.ResponseUserDTO;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.FindByIdException;
import com.dh.clinica.persistence.entity.users.User;
import com.dh.clinica.dto.UserDto;
import com.dh.clinica.persistence.repository.IUserRepository;
import com.dh.clinica.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService, IUserService {
    private final Logger logger = Logger.getLogger(UserServiceImpl.class);
    private final IUserRepository userRepository;

    @Autowired
    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User email not found"));
    }

    public ResponseUserDTO registrar(UserDto appUser) {
        logger.debug("Iniciando método registrar usuario");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        User user = userRepository.save(appUser.toEntity());
        logger.debug("Terminó la ejecución del método registrar usuario");
        return new ResponseUserDTO(user);
    }

    @Override
    public ResponseUserDTO buscar(Integer id) throws FindByIdException {
        logger.debug("Iniciando método buscar usuario por ID");
        if (!userRepository.existsById(id)) {
            throw new FindByIdException("No existe un usuario con el id ingresado");
        }
        logger.debug("Terminó la ejecución del método buscar usuario por ID");
        return new ResponseUserDTO(userRepository.getById(id));
    }

    public List<ResponseUserDTO> buscarTodos() {
        logger.debug("Iniciando método buscar todos los usuarios");
        List<ResponseUserDTO> userDto = new ArrayList<>();
        for (User user: userRepository.findAll()) {
            userDto.add(new ResponseUserDTO(user));
        }
        logger.debug("Terminó la ejecución del método buscar todos los usuarios");
        return userDto;
    }

    public ResponseUserDTO buscarPorEmail(String email) {
        logger.debug("Iniciando método buscar usuario por email");
        Optional<User> appUser = userRepository.findByEmail(email);
        if (appUser.isEmpty()) {
            throw new UsernameNotFoundException("No existe un usuario con el email ingresado");
        }
        logger.debug("Terminó la ejecución del método buscar usuario por email");
        return new ResponseUserDTO(appUser.get());
    }

    @Override
    public String eliminar(Integer id) throws FindByIdException {
        return null;
    }

    @Override
    public ResponseUserDTO actualizar(UserDto userDto) throws FindByIdException {
        return null;
    }
}
