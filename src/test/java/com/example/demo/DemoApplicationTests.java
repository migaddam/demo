package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.User;
import com.example.demo.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest
class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	private static ObjectMapper mapper = new ObjectMapper();

	@Test
	public void testGetExample() throws Exception {
		List<User> users = new ArrayList<>();
		User user = new User();
		user.setId(1);
		user.setName("Arun");
		users.add(user);
		Mockito.when(userService.getUsers()).thenReturn(users);
		mockMvc.perform(get("/getMapping")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].name", Matchers.equalTo("Arun")));
	}

	@Test
	public void testPostExample() throws Exception {
		User user = new User();
		user.setId(1);
		user.setName("Arun");
		Mockito.when(userService.saveUser(ArgumentMatchers.any())).thenReturn(user);
		String json = mapper.writeValueAsString(user);
		mockMvc.perform(post("/postMapping").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
						.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(jsonPath("$.id", Matchers.equalTo(1)))
				.andExpect(jsonPath("$.name", Matchers.equalTo("Arun")));
	}

	@Test
	public void testPutExample() throws Exception {
		User user = new User();
		user.setId(2);
		user.setName("John");
		Mockito.when(userService.updateUser(ArgumentMatchers.any())).thenReturn(user);
		String json = mapper.writeValueAsString(user);
		mockMvc.perform(put("/putMapping").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
						.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", Matchers.equalTo(2)))
				.andExpect(jsonPath("$.name", Matchers.equalTo("John")));
	}

	@Test
	public void testDeleteExample() throws Exception {
		Mockito.when(userService.deleteUser(ArgumentMatchers.anyString())).thenReturn("User is deleted");
		MvcResult requestResult = mockMvc.perform(delete("/deleteMapping").param("user-id", "1"))
				.andExpect(status().isOk()).andExpect(status().isOk()).andReturn();
		String result = requestResult.getResponse().getContentAsString();
		assertEquals(result, "User is deleted");
	}

}
