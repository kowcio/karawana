package tests;

import static org.fest.assertions.Assertions.assertThat;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import skele.Application;
import skele.entities.User;
import skele.repositories.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)   // 1
@SpringApplicationConfiguration(classes = Application.class)   // 2
@WebAppConfiguration   // 3
@IntegrationTest("server.port:8083")   // 4
//http://www.jayway.com/2014/07/04/integration-testing-a-spring-boot-application/
public class AuditTest {

    @Autowired
    UserRepository ur;
        
    @Test
    public void Audittest(){
        System.out.println("TEST_wet2345 ");

        
    User user = new User();
    user.setName("name");

    assertThat(null != user.getId());

    
    User user2 = ur.save(user);
    
    assertThat(user.getCreatedDate() != null);
    assertThat("name".equals(   user2.getName() ));
    assertThat(user.getCreatedDate() != null);
    assertThat(user.getModifiedBy() != null);
    
    System.err.println(user2.toString());
    System.err.println("END TEST ");

    
    
    }
    
    
    
    
}
