package com.org.citius.pms.user.config.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;

public class FreeMarkerConfigurationBean {

	@Bean
	public FreeMarkerConfigurer freemarkerClassLoaderConfig() {
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_27);
		TemplateLoader templateLoader = new ClassTemplateLoader(this.getClass(), "/templates");
		configuration.setTemplateLoader(templateLoader);
		FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
		freeMarkerConfigurer.setConfiguration(configuration);
		return freeMarkerConfigurer;
	}
}
