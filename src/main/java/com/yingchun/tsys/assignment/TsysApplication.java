package com.yingchun.tsys.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EntityScan("com.yingchun.tsys.assignment.model")
@EnableJpaRepositories("com.yingchun.tsys.assignment.repo")
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.yingchun.tsys.assignment"})
//@EnableSwagger2
public class TsysApplication {

	public static void main(String[] args) {
		SpringApplication.run(TsysApplication.class, args);
	}
}
