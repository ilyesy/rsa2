package com.talan.rsa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableConfigurationProperties
@EnableSpringDataWebSupport
public class RsaApplication {

	public static void main(String[] args) { 
		ApplicationContext ctx = SpringApplication.run(RsaApplication.class, args);
//		for(String name : ctx.getBeanDefinitionNames()){
//			System.out.println(name);
//		}
		
	}
}
