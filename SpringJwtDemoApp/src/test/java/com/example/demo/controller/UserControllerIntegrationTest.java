package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.demo.SpringJwtDemoAppApplication;
import com.example.demo.payload.SignUpRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringJwtDemoAppApplication.class,webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	private HttpHeaders headers = new HttpHeaders();
	
	
	@Test
	public void testGetTicketById() throws Exception {
		
		SignUpRequest signUpRequest = new SignUpRequest();
		signUpRequest.setName("Neetu");
		signUpRequest.setUsername("nchauhan");
		signUpRequest.setEmail("nchauhan@gmail.com");
		signUpRequest.setPassword("1234");
		
		
		String inputInJson = this.mapToJson(signUpRequest);
		
		String URIToCreateUser = "/api/auth/signup/ROLE_USER";
		HttpEntity<SignUpRequest> entity = new HttpEntity<SignUpRequest>(signUpRequest, headers);
		testRestTemplate.exchange(formFullURLWithPort(URIToCreateUser),
				HttpMethod.POST, entity, String.class);
	
		String URI = "/api/user/1";

	    String bodyJsonResponse = testRestTemplate.getForObject(formFullURLWithPort(URI), String.class);
		assertThat(bodyJsonResponse).isEqualTo(inputInJson);
	}
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

	private String formFullURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
