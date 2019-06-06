package com.everest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.everest.kafka.service.Consumer;
import com.everest.kafka.service.Producer;


@SpringBootApplication
public class KafkaSqlApplication implements CommandLineRunner{
	
	@Autowired
	private Producer producer;
	
	@Autowired
	private Consumer consumer;
	
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaSqlApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		this.producer.sendMessage("Hello world 456");
		while (true) {
			this.consumer.processMessage();
		}
	}
}