package edu.escuelaing.arem.ASE.app.APP_LB_RoundRobin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;

@SpringBootApplication
public class AppLbRoundRobinApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(AppLbRoundRobinApplication.class);
		app.setDefaultProperties(Collections.<String, Object>singletonMap("server.port", getPort()));
		app.run(args);
	}
	private static int getPort() {
		if (System.getenv("PORT") != null) {
			return Integer.parseInt(System.getenv("PORT"));
		}
		return 6000;
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("GET", "POST","PUT", "DELETE");
			}
		};
	}
}
