package ru.kata.spring.boot_security.demo.configs;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;

    public DataInitializer(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        if (!roleService.getAllRoles().isEmpty()) return;

        Role roleUser = roleService.saveRole(new Role("ROLE_USER"));
        Role roleAdmin = roleService.saveRole(new Role("ROLE_ADMIN"));

        userService.addUser(new User("admin", "admin", "Admin", "Adminov", "admin@example.com", Set.of(roleUser, roleAdmin)));
        userService.addUser(new User("user", "user", "User", "Userov", "user@example.com", Set.of(roleUser)));
    }
}