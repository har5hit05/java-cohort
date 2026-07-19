package com.harshit.springSecurity;

import com.harshit.springSecurity.entities.User;
import com.harshit.springSecurity.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSecurityApplicationTests {

	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {

		User user = new User(4L, "harshit@gmail.com", "1234", "Harshit");

		String token = jwtService.generateToken(user);

		System.out.println(token);

		Long id = jwtService.getUserIdFromToken(token);

		System.out.println(id);

	}

}
