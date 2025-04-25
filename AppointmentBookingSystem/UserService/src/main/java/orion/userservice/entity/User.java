package orion.userservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name ="id", nullable = false, updatable = false)
    private UUID id;

    private String firstname;

    private String lastname;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Date birthday;

    private String phone;

    private String address;

    @Column(name ="created_at", nullable = false, updatable = false)
    private Timestamp created;

    @PrePersist
    protected void onCreate() {
        created = new Timestamp(System.currentTimeMillis());
        id = UUID.randomUUID();
    }
}
