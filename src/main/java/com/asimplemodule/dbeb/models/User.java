package com.asimplemodule.dbeb.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "users")
public class User implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7137240391083137224L;


	@Id
    @GeneratedValue
    private long id;

    
    @Column(name = "facebookId", length = 65,unique=true)
    private String facebookId;
    
    @Column(name = "password", length = 12)
    private String password;
    
    
    
    @Column(name = "firstName", length = 65)
    private String firstName;
    
    
    
    @Column(name = "lastName", length = 65)
    private String lastName;
    
    
    @Column(name = "facebookIdentifier")
    private String facebookIdentifier;
    
    
    
    
    public String getPictureUrl() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("https://graph.facebook.com/");
    	sb.append(this.facebookIdentifier);
    	sb.append("/picture");
    	return sb.toString();
    }
    
    
    public String getFacebookUrl() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("http://facebook.com/");
    	sb.append(this.facebookIdentifier);
    	
    	return sb.toString();
    }
    
    
    
    public String getFullName() {
    	return this.firstName +" "+ this.lastName;
    }
    
   
}
