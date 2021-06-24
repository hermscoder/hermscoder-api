package com.herms.hermscoder.model.cloudinary;

import java.util.HashMap;
import java.util.Map;

public class CloudinarySettings {
    private String cloudName;
    private String apiKey;
    private String apiSecret;

    public String getCloudName() {
        return cloudName;
    }

    public void setCloudName(String cloudName) {
        this.cloudName = cloudName;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public Map<String, String> getConfigMap(){
        Map<String, String> configmap = new HashMap<>();
        configmap.put("cloud_name", getCloudName());
        configmap.put("api_key", getApiKey());
        configmap.put("api_secret", getApiSecret());
        return configmap;
    }
}
