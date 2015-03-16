package allinone.tests.backendunits;

import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.annotations.Test;

import allinone.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest("server.port:8083")
public class AppRunningContextsForTests {
    
    @Test
    public void checkContextRunWithDBCreationAndMigrations() {
        
    }
    
}
