package net.birelian.microservices.greetingservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableFeignClients
public class GreetingServiceApplication {

	private final Environment environment;
	private final TimeService timeService;

	@Value("${greeting}")
	private String greeting;

	public GreetingServiceApplication(Environment environment, TimeService timeService) {

		this.environment = environment;
		this.timeService = timeService;
	}

	public static void main(String[] args) {

		SpringApplication.run(GreetingServiceApplication.class, args);
	}

	@GetMapping
	public String sayHello() {

		return greeting + ": port " + environment.getProperty("server.port") + " at time " + timeService.getTime();
	}

}

