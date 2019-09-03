package io.codeagainsthumanity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.io.IOException;

import static io.codeagainsthumanity.PopulateDeckGSON.readBlackFileGSON;
import static io.codeagainsthumanity.PopulateDeckGSON.readWhiteFileGSON;

@SpringBootApplication
public class CodeagainsthumanityApplication extends SpringBootServletInitializer {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(CodeagainsthumanityApplication.class, args);
		System.out.println("http://localhost:8080/");
		System.out.println("http://localhost:8080/login");
		System.out.println("http://localhost:8080/registration");

		readWhiteFileGSON();
		readBlackFileGSON();
	}

}
