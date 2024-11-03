package lk.ijse.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class StudentDTO {

    private String id;
    private String name;
    private int age;
    private String NIC;
    private String phoneNumber;
    private String email;
    private String address;

}
