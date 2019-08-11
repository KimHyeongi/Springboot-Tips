package com.tistory.eclipse4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ServletComponentScan
@ComponentScan(basePackages= {"com.tistory.eclipse4j"})
@SpringBootApplication
public class ApiApplication extends SpringBootServletInitializer {
        public static void main(String[] args) {
                SpringApplication.run(ApiApplication.class, args);
        }
}
