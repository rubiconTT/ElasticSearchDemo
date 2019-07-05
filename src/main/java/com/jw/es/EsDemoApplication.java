package com.jw.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;

@SpringBootApplication
public class EsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsDemoApplication.class, args);
	}

}
