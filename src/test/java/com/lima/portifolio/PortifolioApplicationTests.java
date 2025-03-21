package com.lima.portifolio;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
	webEnvironment = SpringBootTest.WebEnvironment.MOCK, 
    classes = { PortifolioApplication.class } 
)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class PortifolioApplicationTests {

	@Test
	void contextLoads()  throws Exception {

	}

}
