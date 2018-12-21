package net.birelian.microservices.greetingservice;

import java.time.LocalDateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TimeServiceApplication {

	private final Environment environment;

	public TimeServiceApplication(Environment environment) {

		this.environment = environment;
	}

	public static void main(String[] args) {

		SpringApplication.run(TimeServiceApplication.class, args);
	}

	@GetMapping
	public String getTime() {

		return LocalDateTime.now() + ": port " + environment.getProperty("server.port");
	}
}

