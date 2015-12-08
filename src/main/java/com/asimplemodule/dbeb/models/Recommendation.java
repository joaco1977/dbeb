package com.asimplemodule.dbeb.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.joda.time.LocalDate;

@Entity
@Table(name = "recommendations")
public class Recommendation {

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
    
    @Column(name = "companyId")
    
    
    
    
    
    private Long companyId;
    
    @Column(name = "detail", length = 1000)
    @Size(min = 10, max = 1000)
    
    
    
    
    private String detail;
    
    @Column(name = "votes")
    
    @Min(value = 0)
    @Max(value = 5)
    
    
    private Integer votes;
    
    @Column(name = "recodate")
    
    
    
    @Past
    
    private LocalDate recodate;
    
    @Column(name = "createUserEmail", length = 65)
    @Size(min = 5, max = 65)
    
    
    
    
    private String createUserEmail;
    

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
    
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
    
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    
    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }
    
    public LocalDate getRecodate() {
        return recodate;
    }

    public void setRecodate(LocalDate recodate) {
        this.recodate = recodate;
    }
    
    public String getCreateUserEmail() {
        return createUserEmail;
    }

    public void setCreateUserEmail(String createUserEmail) {
        this.createUserEmail = createUserEmail;
    }
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recommendation)) return false;
        Recommendation that = (Recommendation) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
