package com.asimplemodule.dbeb.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "locations")
public class Location implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5656197963107189548L;


	@Id
    @GeneratedValue
    private long id;

    
    @Column(name = "formattedAddress")
    @Size(min = 4)
    private String formattedAddress;
    
    @Column(name = "longitud")
    private Double longitude;
    
    @Column(name = "latitud")
    private Double latitude;
    

    
   
}
