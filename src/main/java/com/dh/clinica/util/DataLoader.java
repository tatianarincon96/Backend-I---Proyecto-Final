package com.dh.clinica.util;
import com.dh.clinica.persistence.entity.users.User;
import com.dh.clinica.persistence.entity.users.UserRoles;
import com.dh.clinica.persistence.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    private IUserRepository userRepository;

    @Autowired
    public DataLoader(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        /*String password = passwordEncoder.encode("soyUser");*/
        String password2 = passwordEncoder.encode("soyAdmin");

        /*userRepository.save(new User("TatianaUser", "userTatiana","taty96@gmail.com",password, UserRoles.USER));*/
        userRepository.save(new User("Administrator", "admin","admin@example.com",password2, UserRoles.ADMIN));
    }
}
