package ru.levelp;

import org.level.web.AppJPAConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "org.levelp.model", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = AppJPAConfiguration.class)
})
@EnableJpaRepositories(basePackages = "org.levelp.model")
@EnableTransactionManagement
@EnableAutoConfiguration
@EntityScan("org.levelp.model")
public class TestConfiguration {
}
