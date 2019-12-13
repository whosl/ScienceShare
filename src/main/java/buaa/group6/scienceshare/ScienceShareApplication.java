package buaa.group6.scienceshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
@EnableDiscoveryClient
public class ScienceShareApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScienceShareApplication.class, args);
    }

}
