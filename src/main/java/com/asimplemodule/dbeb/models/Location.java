package com.asimplemodule.dbeb.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.joda.time.LocalDate;

@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue
    private long id;

    
    @Column(name = "formattedAddress")
    @Size(min = 4)
    
    
    
    
    private String formattedAddress;
    
    @Column(name = "longitud")
    
    
    
    
    
    private Double longitud;
    
    @Column(name = "latitud")
    
    
    
    
    
    private Double latitud;
    

    public long getId() {
        return id;
    }

    
    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }
    
    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
    
    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location that = (Location) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
