package com.jb.handicap;

import com.jb.handicap.utils.Art;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HandicapApplication {

	public static void main(String[] args) {
		SpringApplication.run(HandicapApplication.class, args);
		System.out.println(Art.HEADER);
	}

}
