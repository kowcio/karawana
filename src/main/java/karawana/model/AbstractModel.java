package karawana.model;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Kowcio on 2016-10-08.
 */
@Data
public class AbstractModel {

    final Logger log = LoggerFactory.getLogger(getClass());
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

}
