package karawana.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import karawana.utils.TestObjectFabric;
import lombok.*;
import org.springframework.web.context.annotation.SessionScope;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
//@SessionScope
@JsonIgnoreProperties(ignoreUnknown=true,value={"hibernateLazyInitializer", "handler"})

public class BeanUser {
    private User user;
    public BeanUser() {
        this.user = TestObjectFabric.getUser();
    }
}
