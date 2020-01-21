package ru.izergin.hometask.config;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource(value="classpath:pg.properties", ignoreResourceNotFound=true)
public class PgConfig extends DriverManagerDataSource {

    @Autowired
    private Environment env;

    @Bean(name = "dataSourcePg")
    public DataSource dataSource() {
        var dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }

    @Bean(name = "jdbcTemplatePg")
    public JdbcTemplate jdbcTemplate() {
        var template = new JdbcTemplate();
        template.setDataSource(dataSource());
        return template;

    }

    @Bean(name = "namedParameterJdbcTemplatePg")
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        var template = new NamedParameterJdbcTemplate(dataSource());
        return template;
    }

}
