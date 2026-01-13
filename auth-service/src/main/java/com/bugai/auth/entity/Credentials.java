package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity
@Table(name="credentials")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credentials {
    @Id
    @Column(nullable=false,updatable=false)
    private UUID id;

    @Column(nullable=false,unique = true)
    private String email;

    @Column(nullable=false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Role role;

    public enum Role {
        ROLE_USER,
        ROLE_ADMIN,
        ROLE_DEVELOPER,
        ROLE_TESTER
    }

    private boolean active = true;

    @PrePersist
    public void prePersist()
    {
        if(id == null)
        {
            id = UUID.randomUUID();
        }
        if(role == null){
            role = Role.ROLE_USER;
        }
    }

}
