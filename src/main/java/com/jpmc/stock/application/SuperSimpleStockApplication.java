package com.jpmc.stock.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {
        "com.jpmc.stock.application"
})
public class SuperSimpleStockApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(SuperSimpleStockApplication.class);
    }

    public static void main(String[] args) {
        try {
            SpringApplication.run(SuperSimpleStockApplication.class, args);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

}