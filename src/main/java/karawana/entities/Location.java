package karawana.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = 0L;

    private Long userId;

    @DateTimeFormat
    @CreatedDate
    private LocalDateTime createdDate;
    private String lat;
    private String lng;

}
