package com.movieinfo.sharewatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SharewatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SharewatchApplication.class, args);
	}

}
