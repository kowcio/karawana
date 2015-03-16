package allinone.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "testEntity")
@Data
@EqualsAndHashCode(callSuper = true)
public class TestEntity extends AbstractEntity {
    
    private static final long serialVersionUID = 1L;
    private String            testString;
    private Long              testLong;
    private String            testSomeStringDate;
    
    @Builder
    public TestEntity() {
        super();
    }
    
}
