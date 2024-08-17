package com.dcplinaWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DcplinaWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(DcplinaWebApplication.class, args);
	}

}
