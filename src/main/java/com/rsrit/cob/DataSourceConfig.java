package com.rsrit.cob;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(value="prod")
@Primary
public class DataSourceConfig {
	
	@Value("${datasource_user_name}")
	public String dataSourceUserName;
	
	@Value("${datasource_user_password}")
	public String dataSourcePassword;
	
	@Value("${datasource_url}")
	public String dataSourceUrl;
	
	
	@Value("${datasource_driver_class}")
	public String dataSourceDriverClassName;
	
	@Bean
	public DataSource dataSource() {
		
		// @formatter:off
	
		return DataSourceBuilder
        .create()
        .username(dataSourceUserName)
        .password(dataSourcePassword)
        .url(dataSourceUrl)
        .driverClassName(dataSourceDriverClassName)
        .build();
		
		// @formatter:on
	}
	
	@PostConstruct
	void displayMessage() {
		System.out.println("Switched to PRODUCTION environment");
	}
	
}
