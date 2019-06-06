package com.everest.kafka.service;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class to send a message to Kafka topic
 * @author User
 *
 */

@Service
public class Producer {

	Logger logger = LoggerFactory.getLogger(Producer.class);
	@Autowired
	private KafkaConfig config;
	
	private Properties props = new Properties();
	
	private KafkaProducer<String, String> producer;
	
	@PostConstruct
	public void init() {
		props.put("bootstrap.servers", this.config.getBootstrapServers());
        props.put("security.protocol", "SSL");
        props.put("ssl.endpoint.identification.algorithm", "");
        props.put("ssl.truststore.location", this.config.getTrustStoreLocation());
        props.put("ssl.truststore.password", this.config.getTrustStorePassword());
        props.put("ssl.keystore.type", "PKCS12");
        props.put("ssl.keystore.location", this.config.getKeystoreLocation());
        props.put("ssl.keystore.password", this.config.getKeystorePassword());
        props.put("ssl.key.password", this.config.getSslKeyPassword());
        props.put("key.serializer",
            "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer",
            "org.apache.kafka.common.serialization.StringSerializer");
        
		producer = new KafkaProducer<>(this.props);
	}
	
	
	public void sendMessage(String message) {
        producer.send(new ProducerRecord<String, String>(this.config.getTopicName(), message));
        logger.debug("Message sent");
	}
	
	@PreDestroy
	public void cleanup() {
		this.producer.close();
	}
	/*
	 public static void main(String[] args) throws IOException {
	        
	        KafkaProducer<String, String> producer = new KafkaProducer<>(this.props);
	        producer.send(new ProducerRecord<String, String>("demo_topic", "test-message"));
	        System.out.println("Message sent");
	        producer.close();
	    }
	    
	    */
}