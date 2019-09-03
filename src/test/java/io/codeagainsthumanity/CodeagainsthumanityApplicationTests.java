package io.codeagainsthumanity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.hamcrest.CoreMatchers.containsString;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CodeagainsthumanityApplicationTests {
	@Autowired
	MockMvc mockMvc;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testRootRoute() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders
				.get("/"))
				.andDo(MockMvcResultHandlers
						.print())
				.andExpect(status()
						.isOk())
				.andExpect(MockMvcResultMatchers
						.content()
						.string(containsString("Routes")));
	}

}
