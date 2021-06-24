package com.herms.hermscoder.config;

import com.herms.hermscoder.model.cloudinary.CloudinaryManager;
import com.herms.hermscoder.model.cloudinary.CloudinarySettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:cloud.properties")
public class CloudConfig {
    @Autowired
    private Environment env;

    @Bean
    public CloudinaryManager cloudinarySettings() {
        CloudinarySettings cs = new CloudinarySettings();
        cs.setCloudName(env.getProperty("cloud.cloudName"));
        cs.setApiKey(env.getProperty("cloud.apiKey"));
        cs.setApiSecret(env.getProperty("cloud.apiSecret"));
        return new CloudinaryManager(cs);
    }
}
