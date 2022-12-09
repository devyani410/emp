package com.empManagement.empAssignement;

import ch.qos.logback.core.encoder.EchoEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

@SpringBootApplication
//@EnableOAuth2Sso
public class EmpAssignementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpAssignementApplication.class, args);
	}

}
