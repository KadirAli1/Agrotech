package main.java.com.agrotech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgrotechApplication {

	@Configuration
	@Import({ThymeleafLayoutInterceptorConfig.class})
	public static class MainConfiguration extends WebMvcConfigurationSupport {


	}

	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(16);
	}

	public static void main(String[] args) {
		SpringApplication.run(AgrotechApplication.class, args);
	}

}
