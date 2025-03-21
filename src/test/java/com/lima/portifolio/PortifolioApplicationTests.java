package com.lima.portifolio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
	webEnvironment = SpringBootTest.WebEnvironment.MOCK, 
    classes = { PortifolioApplication.class },
	properties = "spring.profiles.active=test"
)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class PortifolioApplicationTests {

	@Autowired
    private ApplicationContext context;

	@Test
	void contextLoads()  {
        assertThat(context).isNotNull();
	}

}
