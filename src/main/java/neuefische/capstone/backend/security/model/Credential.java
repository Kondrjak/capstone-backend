package neuefische.capstone.backend.security.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CREDENTIAL")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Credential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique=true)
    @NotNull
    @Size(max = 32)
    private String username;

    @NotNull
    @Size(max = 256)
    private String password;

}
