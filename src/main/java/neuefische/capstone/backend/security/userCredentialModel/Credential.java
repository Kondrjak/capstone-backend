package neuefische.capstone.backend.security.userCredentialModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Credential {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique=true)
    @NotNull
    @Size(max = 32)
    String username;

    @NotNull
    @Size(max = 128)
    String password;
}
