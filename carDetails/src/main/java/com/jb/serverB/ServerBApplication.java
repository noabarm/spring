package com.jb.serverB;

import com.jb.serverB.utils.Art;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerBApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerBApplication.class, args);
		System.out.println(Art.HEADER);
	}

}
