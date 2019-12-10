package buaa.group6.scienceshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
@EnableEurekaClient
public class ScienceShareApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScienceShareApplication.class, args);
    }

}
