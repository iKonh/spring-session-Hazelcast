package com.example.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.GroupConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {

    @Bean
    public Config config() {
        Config config = new Config();

        // ManagerCenter Configuration
//        ManagementCenterConfig managementCenterConfig = config.getManagementCenterConfig();
//        managementCenterConfig.setEnabled(true).setUrl("http://192.168.2.224:8080/mancenter");
//        config.setManagementCenterConfig(managementCenterConfig);

        GroupConfig groupConfig = config.getGroupConfig();
        groupConfig.setName("Hazelcast-Dev");
        groupConfig.setPassword("Hazelcast-Dev");

        return config;
    }
}
