package com.asimplemodule.dbeb.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tags")
public class Tag implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7624281660829481097L;

	@Id
    @GeneratedValue
    private long id;

    @JsonProperty(value="text")
    @Column(name = "name",unique=true)
    private String name;
    
    
   
    
   
}
