//package karawana.entities;
//
//import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.MappedSuperclass;
//import javax.persistence.Version;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//
//@Data
//@MappedSuperclass
//public class AbstractEntity  {
//
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    protected Long id = 0L;
//
//    @Version
//    private Long version;
//
//    public Long getId() {
//        return id;
//    }
//
//    public boolean isNew() {
//       return id != null;//? true : false;
//    }
//
//    public AbstractEntity() {
//    }
//}
