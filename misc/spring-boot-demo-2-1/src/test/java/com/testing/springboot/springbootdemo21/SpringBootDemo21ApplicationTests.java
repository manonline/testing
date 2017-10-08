package com.testing.springboot.springbootdemo21;

import com.testing.springboot.springbootdemo21.controller.IndexController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemo21ApplicationTests {

	private MockMvc mvc;

	@Before
	public void before() {
		this.mvc = MockMvcBuilders.standaloneSetup(new IndexController()).build();
	}

	@Test
	public void contextLoads() throws Exception {
		RequestBuilder req = get("/index");

		mvc.perform(req)
				.andExpect(status().isOk())
				.andExpect(content().string("hello world"));
	}

}
