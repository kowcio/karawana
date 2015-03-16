package allinone.tests.backendunits;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import uk.co.jemos.podam.api.PodamFactoryImpl;
import allinone.Application;
import allinone.WebXml;
import allinone.entities.TestEntity;
import allinone.repositories.TestEntityRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { Application.class, WebXml.class })
@IntegrationTest("server.port:8083")
@WebAppConfiguration
public class RepoTests {
    
    @Autowired
    TestEntityRepository repo;

    public final Logger  log = LoggerFactory.getLogger(getClass());
    
    @Test
    public void checkContextRunWithDBCreationAndMigrations() {
        
        PodamFactoryImpl f = new PodamFactoryImpl();
        repo.deleteAll();
        for (int i = 0; i < 10; i++) {
            TestEntity t = f.manufacturePojo(TestEntity.class);
            log.info("Gen = {}", t.toString());
            
            repo.save(t);
        }
        
        repo.findAll().forEach(c -> System.out.println("Get = " + c.toString()));
        
        repo.findByTestStringLike("TestStringAdded");
        
    }
    
}
