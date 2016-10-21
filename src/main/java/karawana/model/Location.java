package karawana.model;

import lombok.Builder;
import lombok.Data;


@Data
@Builder

public class Location {
    long x;
    long y;
}
