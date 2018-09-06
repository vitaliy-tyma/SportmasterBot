package com.pioneersoft.sportmasterbot;

import com.pioneersoft.sportmasterbot.util.LogManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class ApplicationRunner
{
    public static void main( String[] args )
    {
        LogManager.writeLogText("-------------------- Start application --------------------");

        ApplicationContext context = SpringApplication.run(new Class<?>[] {ApplicationRunner.class}, args);
    }
}