package karawana.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
    protected Long id = 0L;

    private Long userId;

    @DateTimeFormat
    @CreatedDate
    private LocalDateTime createdDate;
    private String lat;
    private String lng;

}
