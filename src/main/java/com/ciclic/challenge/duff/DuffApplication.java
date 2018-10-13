package com.ciclic.challenge.duff;

import com.ciclic.challenge.duff.domain.Beer;
import com.ciclic.challenge.duff.repository.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DuffApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DuffApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BeerRepository repository) {
		return (args) -> {

			repository.save(new Beer(1l, "Weissbier", -1, 3));
			repository.save(new Beer(2l, "Pilsens", -2, 4));
			repository.save(new Beer(3l, "Weizenbier", -4, 6));
			repository.save(new Beer(4l, "Red", -5, 5));
			repository.save(new Beer(5l, "India", -6, 7));
			repository.save(new Beer(6l, "IPA", -7, 10));
			repository.save(new Beer(7l, "Dunkel", -8, 2));
			repository.save(new Beer(8l, "Imperial", -10, 13));
			repository.save(new Beer(9l, "Brown", 0, 14));
		};
	}
}
