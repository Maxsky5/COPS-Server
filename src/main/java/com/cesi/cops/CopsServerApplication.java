package com.cesi.cops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.cesi.cops")
@SpringBootApplication
public class CopsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CopsServerApplication.class, args);
    }
}
