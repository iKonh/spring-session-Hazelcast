package com.example.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.config.ManagementCenterConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {

    @Value("http://${hazelcast.mancenter}/mancenter")
    protected String url;

    @Value("${hazelcast.group.name}")
    protected String groupName;

    @Value("${hazelcast.group.password}")
    protected String groupPassword;

    @Bean
    public Config config() {
        Config config = new Config();

        // ManagerCenter Configuration
        ManagementCenterConfig managementCenterConfig = config.getManagementCenterConfig();
        managementCenterConfig.setEnabled(true).setUrl("http://192.168.2.224:8080/mancenter");
        config.setManagementCenterConfig(managementCenterConfig);

        GroupConfig groupConfig = config.getGroupConfig();
        groupConfig.setName("Hazelcast-Dev");
        groupConfig.setPassword("Hazelcast-Dev");

        return config;
    }
}
