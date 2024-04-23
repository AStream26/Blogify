package com.app.blog.runners;

import com.app.blog.entity.Role;
import com.app.blog.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleSeeder implements CommandLineRunner {

    @Autowired
    IRoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("RoleSeeder.run");

        Role admin = new Role();
        admin.setRole("ROLE_ADMIN");

        Role user = new Role();
        user.setRole("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(admin);
        roles.add(user);
        roleRepository.saveAll(roles);
        System.out.println("Roles Created Successfully");

    }
}
