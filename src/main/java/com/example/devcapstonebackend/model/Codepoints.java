package com.example.devcapstonebackend.model;

import com.sun.istack.NotNull;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import java.util.Collection;

@Embeddable
public class Codepoints{
    @NotNull
    private Collection<String> points;

}
