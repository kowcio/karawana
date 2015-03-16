package allinone.tests;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import allinone.Application;
import allinone.entities.UserEntity;
import allinone.repositories.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest("server.port:8083")
@WebAppConfiguration

public class AuditTest {
    
    @Autowired
    UserRepository ur;
    
    @Test
    public void Audittest() {
        System.out.println("TEST_wet2345 ");
        
        UserEntity user = new UserEntity();
        user.setName("name");
        
        assertThat(null != user.getId());
        
        UserEntity user2 = ur.save(user);
        
        assertThat(user.getCreatedDate() != null);
        assertThat("name".equals(user2.getName()));
        assertThat(user.getCreatedDate() != null);
        assertThat(user.getModifiedBy() != null);
        
        System.err.println(user2.toString());
        System.err.println("END TEST ");
        
    }
    
}
