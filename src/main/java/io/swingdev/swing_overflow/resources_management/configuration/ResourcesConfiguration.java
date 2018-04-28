package io.swingdev.swing_overflow.resources_management.configuration;

import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResourcesConfiguration {
    @Bean
    public Mapper dozerBeanMapper() {
        return DozerBeanMapperBuilder
            .create()
            .withMappingFiles("mapping.xml")
            .build();
    }
}
