package io.tchepannou.academy.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableJpaRepositories("io.tchepannou.academy.user")
@EntityScan("io.tchepannou.academy.user.domain")
public class Application {
    public static void main(final String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
