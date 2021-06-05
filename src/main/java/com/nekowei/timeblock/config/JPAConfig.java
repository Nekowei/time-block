package com.nekowei.timeblock.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.nekowei.timeblock.repo")
@EnableTransactionManagement
public class JPAConfig {


}
