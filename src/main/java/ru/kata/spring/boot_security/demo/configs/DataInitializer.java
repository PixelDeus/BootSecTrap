package ru.kata.spring.boot_security.demo.configs;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (roleRepository.count() > 0) return;

        Role roleUser = roleRepository.save(new Role("ROLE_USER"));
        Role roleAdmin = roleRepository.save(new Role("ROLE_ADMIN"));

        User admin = new User("admin", passwordEncoder.encode("admin"), "Admin", "Adminov", 35, "admin@example.com", Set.of(roleUser, roleAdmin));
        User user = new User("user", passwordEncoder.encode("user"), "User", "Userov", 30, "user@example.com", Set.of(roleUser));

        userRepository.save(admin);
        userRepository.save(user);
    }
}
