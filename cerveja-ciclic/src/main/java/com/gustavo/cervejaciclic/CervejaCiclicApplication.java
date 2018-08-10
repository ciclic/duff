package com.gustavo.cervejaciclic;

import com.gustavo.cervejaciclic.model.Cerveja;
import com.gustavo.cervejaciclic.repository.CervejaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class CervejaCiclicApplication {

	public static void main(String[] args) {
		SpringApplication.run(CervejaCiclicApplication.class, args);
	}
}


@Component
class CreateDb implements CommandLineRunner {

	@Autowired
	private CervejaRepository repository;

	@Override
	public void run(String... args) throws Exception {
		repository.save(new Cerveja("Weissbier", -1, 3));
		repository.save(new Cerveja("Pilsens"	,-2 , 4));
		repository.save(new Cerveja("Weizenbier",	-4, 6));
		repository.save(new Cerveja("Red ale"	,-5, 5));
		repository.save(new Cerveja("India pale ale",	-6, 7));
		repository.save(new Cerveja("IPA"	,-7, 10));
		repository.save(new Cerveja("Dunkel",	-8 , 2));
		repository.save(new Cerveja("Imperial Stouts",	-10, 13));
		repository.save(new Cerveja("Brown ale",0,14));

	}
}