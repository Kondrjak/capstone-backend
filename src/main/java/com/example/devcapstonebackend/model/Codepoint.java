package com.example.devcapstonebackend.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.Collection;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Codepoint {
    @NotNull
    private String codepoint;


}
