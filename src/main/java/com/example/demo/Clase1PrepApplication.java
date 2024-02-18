package com.example.demo;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.Content;
import com.example.demo.model.Status;
import com.example.demo.model.Type;
import com.example.demo.repository.ContentRepository;

@SpringBootApplication
public class Clase1PrepApplication {

	public static void main(String[] args) {
		SpringApplication.run(Clase1PrepApplication.class, args);
	}
	@Bean
	CommandLineRunner init(ContentRepository repository) {
		return args -> {
			Content content = new Content(
					1,
					"Blog about new elements of Java",
					"This blog is a new release of the new",
					Status.IDEA,
					Type.VIDEO,
					LocalDateTime.now(),
					null,
					"myurl.com");
			repository.save(content);
			Content content1 = new Content(
					2,
					"Blog about Python new advances",
					"This blog is a new release of the Python world",
					Status.COMPLETED,
					Type.CONFERENCE_TALK,
					LocalDateTime.now(),
					null,
					"pythonurl.com");
			repository.save(content1);
		};
	}
}
