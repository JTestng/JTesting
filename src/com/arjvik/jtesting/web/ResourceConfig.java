package com.arjvik.jtesting.web;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;

@ApplicationPath("/")
public class ResourceConfig extends org.glassfish.jersey.server.ResourceConfig {

	public ResourceConfig() {
		packages("com.arjvik.jtesting.web");
		register(FreemarkerMvcFeature.class);
		property("jersey.config.server.mvc.templateBasePath.freemarker", "/WEB-INF/freemarker");
		property("jersey.config.server.mvc.factory.freemarker", "com.arjvik.jtesting.web.FreemarkerConfigurationFactory.java");
	}
	
}
