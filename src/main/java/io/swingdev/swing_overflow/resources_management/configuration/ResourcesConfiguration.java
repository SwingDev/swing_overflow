package io.swingdev.swing_overflow.resources_management.configuration;

import com.google.common.collect.ImmutableList;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResourcesConfiguration {
    @Bean
    public Mapper dozerBeanMapper() {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.setMappingFiles(ImmutableList.of("mapping.xml"));

        return dozerBeanMapper;
    }
}
