package com.actualizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value={"file:${ACTUALIZER_CONFIG}"})
public class ActualizerApplication extends SpringBootServletInitializer
		{

	@Override
protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return builder.sources(ActualizerApplication.class);
}


	public static void main(String[] args) {
		SpringApplication.run(ActualizerApplication.class, args);
	}

}
