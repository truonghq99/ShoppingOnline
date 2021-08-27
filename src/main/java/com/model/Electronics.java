package com.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
@DiscriminatorValue("electronics")
public class Electronics extends Item{
    @Column(name="description")
    private String description;
    @Column(name="version")
    private String version;
}
