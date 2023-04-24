package com.example.authjava;

import com.example.authjava.Data.DTOs.ProductRequest;
import com.example.authjava.Data.Models.UserModel;
import com.example.authjava.Repositories.RoleRepository;
import com.example.authjava.Repositories.UserRepository;
import com.example.authjava.Services.ProductService;
import com.example.authjava.Services.RoleService;
import com.example.authjava.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

import java.math.BigDecimal;


@SpringBootApplication
public class AuthJavaApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthJavaApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService, RoleService roleService, ProductService productService) {
        return args -> {
            UserModel user = userService.saveUser(new UserModel(
                null,
                "Super",
                "Admin",
                "123",
                "admin@mail.com",
                true
            ));
            roleService.addRoleToUser("ADMIN", user.getEmail());
            productService.create(new ProductRequest(
                user.getId(),
                "Producto 1",
                "Descrip. Pdto. 1",
                new BigDecimal(12000)
            ));

        };
    }
}
