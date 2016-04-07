package com.example.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.config.Config;
import com.hazelcast.config.DiscoveryConfig;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

/**
 * Created by Huangyipeng on 2016/04/07.
 */
@EnableHazelcastHttpSession
public class HazelcastHttpSessionConfig {

    @Bean
    public HazelcastInstance embeddedHazelcast() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getNetworkConfig().addAddress("192.168.2.37");
        clientConfig.setGroupConfig(new GroupConfig().setName("Hazelcast-Dev").setPassword("Hazelcast-Dev"));
        return HazelcastClient.newHazelcastClient(clientConfig);
    }

}
