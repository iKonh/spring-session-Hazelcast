package com.example.service;

import com.example.entity.TestMessage;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestHazelcastService {

    public static String MAP_NAME = "HAZELCAST_TEST";

    public static String TEST_MESSAGE_KEY = "TEST_MESSAGE";

    @Autowired
    protected HazelcastInstance hazelcastInstance;

    private IMap<String, Object> getMessageUidMap() {
        return hazelcastInstance.getMap(MAP_NAME);
    }

    public TestMessage getMessage() {
        IMap<String, Object> map = getMessageUidMap();
        if (map.containsKey(TEST_MESSAGE_KEY)) {

            return (TestMessage)getMessageUidMap().get(TEST_MESSAGE_KEY);
        } else {
            return null;
        }
    }

    public void saveMessage(TestMessage testMessage) {
        IMap<String, Object> map = getMessageUidMap();
        map.put(TEST_MESSAGE_KEY, testMessage);
    }
}
