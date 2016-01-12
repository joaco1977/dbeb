package com.asimplemodule.dbeb.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue
    private long id;

    
    @Column(name = "name")
    private String name;
    
    
   
    
   
}
