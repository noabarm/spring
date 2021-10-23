package com.jb.recall;

import com.jb.recall.utils.Art;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecallApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecallApplication.class, args);
		System.out.println(Art.HEADER);
	}

}
