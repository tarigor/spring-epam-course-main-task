package com.epam.movieTheater;

import com.epam.movieTheater.configuration.ConfigService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class MovieTheaterApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieTheaterApplication.class, args);
	}
}
