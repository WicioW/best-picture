package com.project.bestpicture.configuration;


import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", sequenceName = "seq", initialValue = 1)
    private Long id;

    public Long getId() {
        return id;
    }
}
