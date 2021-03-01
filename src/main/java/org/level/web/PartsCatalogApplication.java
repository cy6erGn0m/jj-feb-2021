package org.level.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication // @Configuration
@ComponentScan(basePackages = {"org.level.web", "org.levelp.model"})
public class PartsCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(PartsCatalogApplication.class, args);
    }

}
