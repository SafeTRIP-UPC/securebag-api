package com.upc.securebagapi;

import com.upc.securebagapi.auth.domain.models.entity.RoleEntity;
import com.upc.securebagapi.auth.domain.models.entity.UserEntity;
import com.upc.securebagapi.auth.domain.models.enums.RoleEnum;
import com.upc.securebagapi.auth.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
public class SecureBagApiApplication {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public static void main(String[] args) {

        SpringApplication.run(SecureBagApiApplication.class, args);

        String url = "http://localhost:8080/swagger-ui/index.html";
        System.out.println("\n• Swagger UI is available at » " + url);
    }

    @Bean
    CommandLineRunner init() {
        return args -> {
            System.out.println("\n• Application is running...\n");

            UserEntity userEntity = UserEntity.builder()
                    .email("wilver.ar.dev@gmail.com")
                    .username("wilver-ar")
                    .password(passwordEncoder.encode("2024"))
                    .roles(Set.of(RoleEntity.builder()
                            .role(RoleEnum.valueOf(RoleEnum.USER.name()))
                            .build()))
                    .build();

            userRepository.save(userEntity);
        };
    }
}
