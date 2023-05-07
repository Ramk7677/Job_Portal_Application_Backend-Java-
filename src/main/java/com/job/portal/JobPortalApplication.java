package com.job.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class JobPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobPortalApplication.class, args);
	}
	/*
	 * @Bean public Docket api() { return new Docket(DocumentationType.SWAGGER_2)
	 * .select() .apis(RequestHandlerSelectors.any()) .paths(PathSelectors.any())
	 * .build(); }
	 */
}
