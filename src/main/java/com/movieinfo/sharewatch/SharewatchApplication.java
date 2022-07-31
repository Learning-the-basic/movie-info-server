package com.movieinfo.sharewatch;

import com.movieinfo.sharewatch.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.filter.reactive.HiddenHttpMethodFilter;

@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class SharewatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SharewatchApplication.class, args);
	}
}
