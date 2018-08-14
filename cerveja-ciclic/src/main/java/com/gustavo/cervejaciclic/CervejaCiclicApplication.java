package com.gustavo.cervejaciclic;

import com.gustavo.cervejaciclic.model.Cerveja;
import com.gustavo.cervejaciclic.repository.CervejaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Component;

@EnableFeignClients("com.gustavo.cervejaciclic")
@SpringBootApplication
@EnableDiscoveryClient
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
		repository.save(new Cerveja("Weissbier", -1, 3, "22g3ztwcw5evonx3dt5cxboda", "37i9dQZF1DWTzoMWoVzs9W"));
		repository.save(new Cerveja("Pilsens"	,-2 , 4, "22g3ztwcw5evonx3dt5cxboda", "37i9dQZF1DWTzoMWoVzs9W"));
		repository.save(new Cerveja("Weizenbier",	-4, 6, "22g3ztwcw5evonx3dt5cxboda", "37i9dQZF1DWTzoMWoVzs9W"));
		repository.save(new Cerveja("Red ale"	,-5, 5, "22g3ztwcw5evonx3dt5cxboda", "37i9dQZF1DWTzoMWoVzs9W"));
		repository.save(new Cerveja("India pale ale",	-6, 7, "22g3ztwcw5evonx3dt5cxboda", "37i9dQZF1DWTzoMWoVzs9W"));
		repository.save(new Cerveja("IPA"	,-7, 10, "22g3ztwcw5evonx3dt5cxboda", "37i9dQZF1DWTzoMWoVzs9W"));
		repository.save(new Cerveja("Dunkel",	-8 , 2, "22g3ztwcw5evonx3dt5cxboda", "37i9dQZF1DWTzoMWoVzs9W"));
		repository.save(new Cerveja("Imperial Stouts",	-10, 13, "22g3ztwcw5evonx3dt5cxboda", "37i9dQZF1DWTzoMWoVzs9W"));
		repository.save(new Cerveja("Brown ale",0,14, "22g3ztwcw5evonx3dt5cxboda", "37i9dQZF1DWTzoMWoVzs9W"));

	}
}