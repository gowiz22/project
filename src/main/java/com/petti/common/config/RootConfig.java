package com.petti.common.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan("com.petti") // 매퍼를 찾아 스프링빈으로 등록함
@PropertySource(value = "classpath:database/db.properties")
public class RootConfig {
	
	@Value("${db.driver}")
	private String driverClassName;
	
	@Value("${db.url}")
	private String jdbcUrl; 
	
	@Value("${db.username}")
	private String username;
	
	@Value("${db.password}")
	private String password;
	
	@Bean
	public DataSource dataSource() {
		HikariConfig config= new HikariConfig(); 
		config.setDriverClassName(driverClassName);
		config.setJdbcUrl(jdbcUrl);
		config.setUsername(username);
		config.setPassword(password);
		return new HikariDataSource(config); 
	}
	
	@Bean
	public SqlSessionFactoryBean sessionFactoryBean() throws IOException {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(dataSource());
		factory.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources("classpath:mappers/**/*Mapper.xml"));
		factory.setTypeAliasesPackage("com.petti.board.domain");
		return factory;
	}
	
	@Bean
	public SqlSessionTemplate sessionTemplate() throws Exception {
		return new SqlSessionTemplate(sessionFactoryBean().getObject());
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource(); 
		messageSource.setBasenames("message.label");
		messageSource.setDefaultEncoding("utf-8");
		return messageSource;
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer(); 
	}
	
}
