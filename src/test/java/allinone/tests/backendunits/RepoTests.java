package allinone.tests.backendunits;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import uk.co.jemos.podam.api.PodamFactoryImpl;
import allinone.ApplicationTest;
import allinone.WebXml;
import allinone.entities.TestEntity;
import allinone.repositories.TestEntityRepository;

//@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { ApplicationTest.class, WebXml.class })
@IntegrationTest("server.port:8083")
//@WebAppConfiguration
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class RepoTests extends AbstractTestNGSpringContextTests {
    
    @Autowired
    TestEntityRepository repo;
    
    public final Logger  log = LoggerFactory.getLogger(getClass());
    
    @Test
    public void checkContextRunWithDBCreationAndMigrations() {
        
        PodamFactoryImpl f = new PodamFactoryImpl();
        repo.deleteAll();
        for (int i = 0; i < 10; i++) {
            TestEntity t = f.manufacturePojo(TestEntity.class);
            t.setId(null);//this makes it work, why ? cause hibernatet know that without id it needs to be persistente ? wtf?
            log.info("Gen = {}", t.toString());
            
            repo.save(t);
        }
        
        repo.findAll().forEach(c -> System.out.println("Get = " + c.toString()));
        
        repo.findByTestStringLike("TestStringAdded");
        
    }
    
}
