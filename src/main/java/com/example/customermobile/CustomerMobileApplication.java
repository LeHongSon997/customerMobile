package com.example.customermobile;

import com.example.customermobile.entity.Role;
import com.example.customermobile.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

@SpringBootApplication
public class CustomerMobileApplication {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(CustomerMobileApplication.class, args);
    }

//    @Resource
//    private RoleRepository roleRepository;

//    @Override
//    public void run(String... args) throws Exception {
//        Role adminRole = new Role();
//        adminRole.setName("ROLE_ADMIN");
//        roleRepository.save(adminRole);
//
//        Role userRole = new Role();
//        userRole.setName("ROLE_USER");
//        roleRepository.save(userRole);
//    }
}
