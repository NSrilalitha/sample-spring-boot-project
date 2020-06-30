package com.sample.spring.boot.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class in which beans are defined.
 * 
 * @author Srilalitha
 *
 */
@Configuration
public class BeanConfiguration {

	@Bean
	public JSONParserService parserService() {
		JSONParserService parserService = new JSONParserService();
		return parserService;
	}
}
