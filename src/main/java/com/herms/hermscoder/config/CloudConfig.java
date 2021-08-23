package com.herms.hermscoder.config;

import com.herms.hermscoder.model.cloudinary.CloudinaryManager;
import com.herms.hermscoder.model.cloudinary.CloudinarySettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@PropertySource("classpath:cloud.properties")
public class CloudConfig {


    @Value("${cloud.cloudName}")
    private String cloudName;
    @Value("${cloud.apiKey}")
    private String apiKey;
    @Value("${cloud.apiSecret}")
    private String apiSecret;


    @Bean
    public CloudinaryManager cloudinarySettings() {
        CloudinarySettings cs = new CloudinarySettings();
        cs.setCloudName(cloudName);
        cs.setApiKey(apiKey);
        cs.setApiSecret(apiSecret);
        return new CloudinaryManager(cs);
    }
}
