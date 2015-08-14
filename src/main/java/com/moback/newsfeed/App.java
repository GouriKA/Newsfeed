package com.moback.newsfeed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class App extends SpringBootServletInitializer {	

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(App.class, ApplicationConfig.class);
    }
    public static void main(String[] args)
    {
    	System.out.println("Starting Newsfeed app..........");
        SpringApplication.run(App.class, args);
    }
}