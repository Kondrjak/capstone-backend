package neuefische.capstone.backend.rest.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class CodepointGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique=true)
    @NotNull
    @Size(max = 128)
    private String name;

    @NotNull
    @ElementCollection
    private List<String> codepoints;

    @NotNull
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Tag> tags;

    @NotNull
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Font> fonts;
}