package com.movieinfo.sharewatch;

import com.movieinfo.sharewatch.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class SharewatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SharewatchApplication.class, args);
    }
}
