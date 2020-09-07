package br.com.digamo.tasks.api.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class WebConfiguration {

    @Bean
    public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource(); 
		
		ms.setBasename("classpath:messages");
		ms.setDefaultEncoding("ISO-8859-1");
		ms.setDefaultLocale(Locale.getDefault());
		
		return ms;
	}

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}
