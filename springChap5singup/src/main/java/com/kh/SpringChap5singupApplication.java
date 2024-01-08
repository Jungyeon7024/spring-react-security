package com.kh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan("com.kh")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringChap5singupApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringChap5singupApplication.class, args);
	}

}
