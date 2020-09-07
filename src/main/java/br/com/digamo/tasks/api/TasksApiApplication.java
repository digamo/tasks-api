package br.com.digamo.tasks.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TasksApiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TasksApiApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TasksApiApplication.class);
	}
}

