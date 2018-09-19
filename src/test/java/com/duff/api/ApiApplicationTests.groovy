package com.duff.api

import org.springframework.boot.test.context.SpringBootContextLoader
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@ContextConfiguration(loader = SpringBootContextLoader, classes = [ApiApplication])
@SpringBootTest(webEnvironment = RANDOM_PORT)
abstract class ApiApplicationTests extends Specification{

	void contextLoads() {
	}

}
