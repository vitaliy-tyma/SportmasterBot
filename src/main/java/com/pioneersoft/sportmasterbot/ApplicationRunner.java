package com.pioneersoft.sportmasterbot;

import com.pioneersoft.sportmasterbot.config.DatabaseConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ApplicationRunner
{
    public static void main( String[] args )
    {
        ApplicationContext context = SpringApplication.run(new Class<?>[] {ApplicationRunner.class, DatabaseConfig.class}, args);
    }
}