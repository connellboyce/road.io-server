package com.group4.capstone.roadio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RoadioApplicationTests {

	@Autowired
	private WebApplicationContext context;


	@Test
	void contextLoads() {
		assertThat(context).isNotNull();
	}
}
