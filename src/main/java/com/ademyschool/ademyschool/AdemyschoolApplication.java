package com.ademyschool.ademyschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.ademyschool.ademyschool.repository")
@EntityScan("com.ademyschool.ademyschool.model")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class AdemyschoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdemyschoolApplication.class, args);
	}

}
