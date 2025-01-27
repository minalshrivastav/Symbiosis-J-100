package com.example.purchase;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@Configuration // @Configuration is a Spring annotation that is used to define a configuration class in a Spring application. Configuration classes are used to define and configure Spring-managed beans programmatically, instead of using XML configuration.
@EnableJpaRepositories(basePackages = "com.example.purchase") 
public class DataConfig {

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/emedicine")
                .username("root")
                .password("minalshri@2611")
                .build();
    }
}

