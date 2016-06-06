package com.example.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.config.Config;
import com.hazelcast.config.DiscoveryConfig;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

/**
 * Created by Huangyipeng on 2016/04/07.
 */
@EnableHazelcastHttpSession
public class HazelcastHttpSessionConfig {

    @Value("http://${hazelcast.mancenter}/mancenter")
    protected String url;

    @Value("${hazelcast.group.name}")
    protected String groupName;

    @Value("${hazelcast.group.password}")
    protected String groupPassword;

    // Client
//    @Bean
//    public HazelcastInstance embeddedHazelcast() {
//        ClientConfig clientConfig = new ClientConfig();
//        clientConfig.getNetworkConfig().addAddress("localhost");
//        clientConfig.setGroupConfig(new GroupConfig().setName("Hazelcast-Dev").setPassword("Hazelcast-Dev"));
//        return HazelcastClient.newHazelcastClient(clientConfig);
//    }

    // Node
    @Bean
    public Config config() {
        Config config = new Config();

        // ManagerCenter Configuration
        ManagementCenterConfig managementCenterConfig = config.getManagementCenterConfig();
        managementCenterConfig.setEnabled(true).setUrl(url);
        config.setManagementCenterConfig(managementCenterConfig);

        GroupConfig groupConfig = config.getGroupConfig();
        groupConfig.setName(groupName);
        groupConfig.setPassword(groupPassword);

        return config;
    }

}
