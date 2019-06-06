package com.everest.kafka.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaConfig {
	@Value("${kafka.bootstrap.servers}")
	private String bootstrapServers;
	@Value("${kafka.consumer.ssl.keystore-location}")
	private String keystoreLocation;
	
	@Value("${kafka.consumer.ssl.truststore-location}")
	private String trustStoreLocation;
	
	@Value("${kafka.consumer.ssl.key-password}")
	private String sslKeyPassword;
	
	@Value("${kafka.consumer.ssl.keystore-password}")
	private String keystorePassword;
	
	
	@Value("${kafka.consumer.ssl.truststore-password}")
	private String trustStorePassword;
	
	@Value("${kafka.topic}")
	private String topicName;
	
	
	
	public String getTrustStoreLocation() {
		return trustStoreLocation;
	}
	public void setTrustStoreLocation(String trustStoreLocation) {
		this.trustStoreLocation = trustStoreLocation;
	}
	public String getSslKeyPassword() {
		return sslKeyPassword;
	}
	public void setSslKeyPassword(String sslKeyPassword) {
		this.sslKeyPassword = sslKeyPassword;
	}
	public String getKeystorePassword() {
		return keystorePassword;
	}
	public void setKeystorePassword(String keystorePassword) {
		this.keystorePassword = keystorePassword;
	}
	public String getTrustStorePassword() {
		return trustStorePassword;
	}
	public void setTrustStorePassword(String trustStorePassword) {
		this.trustStorePassword = trustStorePassword;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getBootstrapServers() {
		return bootstrapServers;
	}
	public void setBootstrapServers(String bootstrapServers) {
		this.bootstrapServers = bootstrapServers;
	}
	public String getKeystoreLocation() {
		return keystoreLocation;
	}
	public void setKeystoreLocation(String keystoreLocation) {
		this.keystoreLocation = keystoreLocation;
	}
	
	
	
	/*
	props.put("bootstrap.servers", "kafka-1eefb686-yuanfamily-4f32.aivencloud.com:11358");
    props.put("security.protocol", "SSL");
    props.put("ssl.endpoint.identification.algorithm", "");
    props.put("ssl.truststore.location", "c:\\aiven\\client.truststore.jks");
    props.put("ssl.truststore.password", "secret");
    props.put("ssl.keystore.type", "PKCS12");
    props.put("ssl.keystore.location", "c:\\aiven\\client.keystore.p12");
    props.put("ssl.keystore.password", "secret");
    props.put("ssl.key.password", "secret");
    props.put("key.serializer",
        "org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer",
        "org.apache.kafka.common.serialization.StringSerializer");
        */
}
