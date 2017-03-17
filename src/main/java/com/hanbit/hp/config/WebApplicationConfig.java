package com.hanbit.hp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/*설정파일 선언하는것*/
@Configuration
public class WebApplicationConfig extends WebMvcConfigurerAdapter {
	/*스태틱한 리소스 css js  같은것*/
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		/*웹스톰 주소로 불러준다*/
		registry.addResourceHandler("/static/**")
		.addResourceLocations("file:/Users/hb/hanbit/webstormpjt/web/dist/");
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
