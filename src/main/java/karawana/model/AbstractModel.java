package karawana.model;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
