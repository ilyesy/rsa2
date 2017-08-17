package com.talan.rsa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class RsaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RsaApplication.class, args);
	}
}
