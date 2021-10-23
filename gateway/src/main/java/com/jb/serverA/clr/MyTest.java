package com.jb.serverA.clr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Order(1)
public class MyTest implements CommandLineRunner {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public void run(String... args) throws Exception {

    }
}
