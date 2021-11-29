package neuefische.capstone.backend.rest.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class KeyLayout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique=true)
    @NotNull
    private String name;

    @Column(unique=true)
    @NotNull
    @NotEmpty
    private String keyLayout;

    @NotNull
    @NotEmpty
    private String author;

    @NotNull
    @NotEmpty
    @ManyToMany
    private List<Tag> tags;

    @NotNull
    @NotEmpty
    @ManyToMany
    private List<Font> fonts;

    @NotNull
    @ElementCollection
    private List<Integer> keysInRow;
}