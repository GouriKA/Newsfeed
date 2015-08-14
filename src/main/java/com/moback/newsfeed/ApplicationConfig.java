package com.moback.newsfeed;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Mac;
import javax.servlet.Filter;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

//import com.moback.newsfeed.filters.AuthenticationFilter;
import com.moback.newsfeed.filters.CORSFilter;
import com.mongodb.MongoClient;

@Configuration
@EnableAutoConfiguration
public class ApplicationConfig {

	private String keyPhrase = "Funny Key Phrase";

	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new MongoClient(), "test1");
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;
	}
	
	@Bean
	public Filter cORSFilter() {
	    return new CORSFilter();
	}
	
	/*@Bean
	public Filter authenticationFilter() {
		return new AuthenticationFilter();
	}*/
		
	/*
	 * Message Authentication Code MAC provides a way to check the integrity of
	 * information transmitted over or stored in an unreliable medium, based on
	 * a secret key.
	 */
	@Bean
	public Mac getMac() throws NoSuchAlgorithmException, InvalidKeyException {
		Mac mac = Mac.getInstance("HmacSHA256");
		Charset charSet = Charset.forName("US-ASCII");
		mac.init(new javax.crypto.spec.SecretKeySpec(charSet.encode(keyPhrase).array(), "HmacSHA256"));
		return mac;
	}

	// cryptographically strong random number generator.
	@Bean
	public SecureRandom getSecureRandom() throws NoSuchAlgorithmException {
		return SecureRandom.getInstance("SHA1PRNG");
	}

	/*
	 * MessageDigest class provides applications the functionality of a message
	 * digest algorithm, such as SHA-1 or SHA-256. Message digests are secure
	 * one-way hash functions that take arbitrary-sized data and output a
	 * fixed-length hash value.
	 */
	@Bean
	public MessageDigest getMessageDigest() throws NoSuchAlgorithmException {
		return MessageDigest.getInstance("SHA-1");
	}
}
