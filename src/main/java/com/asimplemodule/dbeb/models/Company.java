package com.asimplemodule.dbeb.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.joda.time.LocalDate;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue
    private long id;

    
    @Column(name = "name", length = 65)
    @Size(min = 4, max = 65)
    
    
    
    
    private String name;
    
    @Column(name = "info", length = 65)
    @Size(min = 4, max = 65)
    
    
    
    
    private String info;
    
    @Column(name = "active")
    
    
    
    
    
    private Boolean active;
    
    @Column(name = "locationId")
    
    
    
    
    
    private Long locationId;
    
    @Column(name = "facebook", length = 65)
    @Size(min = 4, max = 65)
    
    
    
    
    private String facebook;
    
    @Column(name = "email", length = 65)
    @Size(min = 4, max = 65)
    
    
    
    
    private String email;
    
    @Column(name = "webSite")
    @Size(min = 10)
    
    
    
    
    private String webSite;
    

    public long getId() {
        return id;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }
    
    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company that = (Company) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
