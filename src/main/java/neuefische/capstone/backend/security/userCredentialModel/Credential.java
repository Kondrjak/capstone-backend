package neuefische.capstone.backend.security.userCredentialModel;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Credential {
    @Id
    long id;

    String username;
    String password;
}
