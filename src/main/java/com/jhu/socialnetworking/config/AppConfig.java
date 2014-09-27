package com.jhu.socialnetworking.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * This holds application specific configuration information in case we need more specific application configuration.
 *
 * @author Nathan Shih
 * @date Sep 26, 2014
 */
@Configuration
@ComponentScan(basePackages = "com.jhu.socialnetworking")
public class AppConfig {
	
	// do nothing for now
}
