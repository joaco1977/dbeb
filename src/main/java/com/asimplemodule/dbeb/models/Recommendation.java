package com.asimplemodule.dbeb.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.criterion.Restrictions;

import com.asimplemodule.dbeb.util.HibernateUtil;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
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
    private String info;
    
    @Column(name = "active")
    private Boolean active;
    
    
    @Column(name = "detail", length = 1000)
    @Size(min = 10, max = 1000)
    private String detail;
    
    @Column(name = "votes")
    @Min(value = 0)
    @Max(value = 5)
    private Integer votes;
    
    @Column(name = "recodate")
    @Past
    private Date recodate;
    
    @Column(name = "createUserEmail", length = 65)
    private String createUserEmail;
    
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="companyId")
    private Company company;
    

    
    public void addRecoTag(Tag tag) {
    	HibernateUtil.getSession().saveOrUpdate(tag);
    	HibernateUtil.getSession().saveOrUpdate(new RecoTag(this.getId(),tag.getId()));
    }
    
    public void removeRecoTag(Tag tag) {
    	HibernateUtil.getSession().delete(new RecoTag(this.getId(),tag.getId()));
    }
    
    public List<Tag> getTags() {
    	return HibernateUtil.getSession().createCriteria(RecoTag.class).add(
    			Restrictions.eq("recommendationId",this.getId())).list();
    	
    }
}
