package hello;

import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import config.JerseyConfig;

@EnableAutoConfiguration
public class Application {
	
	public static void main(String... args) {
		new SpringApplicationBuilder(Application.class).showBanner(true)
													   .run(args);
	}
	
	@Bean
	public ServletRegistrationBean jerseyServlet() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new ServletContainer(), "/*");
		registrationBean.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyConfig.class.getName());
		
		return registrationBean;
	}
}
