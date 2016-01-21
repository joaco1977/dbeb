package com.asimplemodule.dbeb.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "companies")
public class Company implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 3473867753947291780L;


	@Id
    @GeneratedValue
    private long id;

    
    @Column(name = "name", length = 65)
    @Size(min = 4, max = 65)
    private String name;
    
    @Column(name = "info", length = 65)
    private String info;
    
    @Column(name = "active")
    private Boolean active;
    
    
    @Column(name = "facebook", length = 65)
    private String facebook;
    
    @Column(name = "email", length = 65)
    @Size(min = 4, max = 65)
    private String email;
    
    @Column(name = "webSite")
    @Size(min = 10)
    private String webSite;
    
    @Column(name = "phone")
    private String phone;
    
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="locationId")
    private Location location;
   
   
}
