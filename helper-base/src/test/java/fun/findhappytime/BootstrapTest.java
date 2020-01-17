package fun.findhappytime;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by zhangshu on 2017/9/12.
 */
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = "com.findhappytime")
@PropertySource({"classpath:application.properties"})
@EnableAutoConfiguration
@SpringBootTest(classes = {BootstrapTest.class})
@AutoConfigureMockMvc
@EnableDiscoveryClient
public class BootstrapTest {

    private static final String INSTANCE_NAME = "SILK-ROAD-CALLER";

    @Autowired
    private DiscoveryClient discoveryClient;

    @Test
    public void capabilityTest() throws JsonProcessingException {
        List<ServiceInstance> services = this.discoveryClient.getInstances(INSTANCE_NAME);
        Assert.assertTrue(services.size() > 0);
    }

}
