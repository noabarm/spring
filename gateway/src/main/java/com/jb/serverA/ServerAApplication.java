package com.jb.serverA;

import com.jb.serverA.utils.Art;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerAApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerAApplication.class, args);
		System.out.println(Art.HEADER);
	}

}
