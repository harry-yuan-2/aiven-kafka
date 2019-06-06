package com.everest.kafka.service;

import java.util.Arrays;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everest.kafka.dao.MessageDao;

@Service
public class Consumer {
	Logger logger = LoggerFactory.getLogger(Consumer.class);
	
	@Autowired
	private MessageDao messageDao;
	
	
	@Autowired
	private KafkaConfig config;

	private Properties props = new Properties();

	private KafkaConsumer<String, String> consumer;

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
		props.put("group.id", "demo_group");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	
		consumer = new KafkaConsumer<>(props);
	}

	public void processMessage() {

		consumer.subscribe(Arrays.asList(this.config.getTopicName()));
		ConsumerRecords<String, String> records = consumer.poll(1000);
		logger.debug("Record size={}", records.count());
		for (ConsumerRecord<String, String> record : records) {
			logger.debug("offset = {}, key = {}, value = {}", record.offset(), record.key(), record.value());
			this.messageDao.insert(record.value());
		}
	}

	@PreDestroy
	public void cleanup() {
		this.consumer.close();
	}

}
