package com.asimplemodule.dbeb.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private long id;

    
    @Column(name = "facebookId", length = 65,unique=true)
    @Size(min = 4, max = 65)
    
    
    
    
    private String facebookId;
    
    @Column(name = "password", length = 12)
    @Size(min = 6, max = 12)
    
    
    
    
    private String password;
    
    
    
    @Column(name = "firstName", length = 65)
    @Size(min = 6, max = 65)

    private String firstName;
    
    
    
    @Column(name = "lastName", length = 65)
    @Size(min = 6, max = 65)
    private String lastName;
    
    
    public long getId() {
        return id;
    }

    
    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    
    
    public String getFullName() {
    	return this.firstName+" "+this.lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User that = (User) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
