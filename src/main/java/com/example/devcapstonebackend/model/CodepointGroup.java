package com.example.devcapstonebackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CodepointGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //todo: make unique in table
    @NotNull
    @Size(max = 128)
    private String name;

    @NotNull
    @ElementCollection
    private List<Codepoint> codepoints;

    @NotNull
    @ManyToMany
    private List<Tag> tags;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Codepoint> getCodepoints() {
        return codepoints;
    }

    public void setCodepoints(List<Codepoint> codepoints) {
        this.codepoints = codepoints;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}