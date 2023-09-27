package com.zefir;

import com.zefir.exception.ApiExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//TODO: adding WebSocket

@SpringBootApplication(scanBasePackages = {"com.zefir"})
public class ZefirApplication {

    @Value("${spring.application.name}")
    private String applicationName;

    public static void main(String[] args) {
        SpringApplication.run(ZefirApplication.class, args);
    }

    @Bean
    public ApiExceptionHandler exceptionHandler() {
        return new ApiExceptionHandler(applicationName);
    }
}
