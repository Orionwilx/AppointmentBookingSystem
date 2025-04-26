package orion.userservice.dto;

import lombok.Data;
import orion.userservice.entity.Role;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Data
public class UserDto {
    private UUID id;
    private String firstname;
    private String lastname;
    private Role role;
    private Date birthday;
    private String phone;
    private String address;
}
