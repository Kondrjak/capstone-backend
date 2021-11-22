package com.example.devcapstonebackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CodepointGroup {

    @Id
    private String name;

    @ElementCollection
    private Collection<String> codepoints;

    @ElementCollection
    private Collection<String> tags;

    public Collection<String> getCodepoints() {
        return codepoints;
    }

    public void setCodepoints(Collection<String> codepoints) {
        this.codepoints = codepoints;
    }

    public Collection<String> getTags() {
        return tags;
    }

    public void setTags(Collection<String> tags) {
        this.tags = tags;
    }
}