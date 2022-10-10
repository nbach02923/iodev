package vn.iodev.contestmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableEurekaClient
public class ContestManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContestManagementSystemApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsMappingConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
					.allowedOrigins("*");
					// .allowedMethods(cors.getAllowedMethods())
					// .maxAge(cors.getMaxAge())
					// .allowedHeaders(cors.getAllowedHeaders())
					// .exposedHeaders(cors.getExposedHeaders());
			}
		};
	}
}
