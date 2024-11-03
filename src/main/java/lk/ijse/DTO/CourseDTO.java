package lk.ijse.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class CourseDTO {

    private String id;
    private String name;
    private String duration;
    private String fee;
}
