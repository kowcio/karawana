package allinone.model;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Kowcio on 2016-10-08.
 */
@Data
public class AbstractModel {

    final Logger log = LoggerFactory.getLogger(getClass());
    String id;

}
