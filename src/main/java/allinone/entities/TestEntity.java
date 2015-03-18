package allinone.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "testEntity")
@Data
@EqualsAndHashCode(callSuper = true)
public class TestEntity extends AbstractEntity {
    
    @Transient
    private static final long serialVersionUID = 1L;
    private String            testString;
    private Long              testLong;
    private String            testSomeStringDate;
    
    @Builder
    public TestEntity() {
        super();
    }
    
}
