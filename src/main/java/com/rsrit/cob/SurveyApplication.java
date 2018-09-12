package com.rsrit.cob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@PropertySource("classpath:application.${profile:development}.properties")
@EnableAspectJAutoProxy 
@Import(DataSourceConfig.class)
@EnableAsync
public class SurveyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SurveyApplication.class, args);
	}
}
