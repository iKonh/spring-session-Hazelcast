package api;

import com.example.TestServer;
import com.example.entity.TestMessage;
import config.CustomRequestFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Huangyipeng on 2016/04/08.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestServer.class)
@IntegrationTest("server.port=0")
public class ResponseTest {

    @Value("http://${app.server1}/test")
    protected String testServer1;

    @Value("http://${app.server2}/test")
    protected String testServer2;

    protected RestTemplate restTemplate = new RestTemplate();

    @Before
    public void setup(){
        restTemplate.setRequestFactory(new CustomRequestFactory());
    }

    @Test
    public void test_cross_10000(){

        for (int index = 0; index < 10000; index++) {
            if (index % 2 == 0) {
                restTemplate.postForObject(testServer1, null, Void.class);
            } else {
                restTemplate.postForObject(testServer2, null, Void.class);
            }
        }

        TestMessage testMessage = restTemplate.getForObject(testServer1, TestMessage.class);
        assertThat(testMessage.count, is(10000));
    }

    @Test
    public void test_backup() {

        for (int index = 0; index < 500; index++) {
            if (index % 2 == 0) {
                restTemplate.postForObject(testServer1, null, Void.class);
            } else {
                restTemplate.postForObject(testServer2, null, Void.class);
            }
        }

        // TODO:Parse:AppServer 1、Hazelcast 1 shutdown
        for (int index = 0; index < 50; index++) {
            restTemplate.postForObject(testServer2, null, Void.class);
        }

        // TODO:Parse:AppServer 1、Hazelcast 1 startup
        TestMessage testMessage = restTemplate.getForObject(testServer1, TestMessage.class);
        assertThat(testMessage.count, is(550));
    }

    @Test
    public void test_addServer() {

        for (int index = 0; index < 100; index++) {
            restTemplate.postForObject(testServer1, null, Void.class);
        }

        // TODO:Parse:AppServer 2、Hazelcast 2 startup
        TestMessage testMessage = restTemplate.getForObject(testServer2, TestMessage.class);
        assertThat(testMessage.count, is(100));
    }
}
