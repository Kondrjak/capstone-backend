package neuefische.capstone.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    @ElementCollection
    private List<String> tags;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCodepoints() {
        return codepoints;
    }

    public void setCodepoints(List<String> codepoints) {
        this.codepoints = codepoints;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}