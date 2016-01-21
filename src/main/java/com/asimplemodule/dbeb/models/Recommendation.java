package com.asimplemodule.dbeb.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
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
public class Recommendation implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2165310019570747480L;


	@Id
    @GeneratedValue
    private long id;

    
    @Column(name = "name")
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
    private Date recodate;
    
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="createUserId")
    private User createUser;
    
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="companyId")
    private Company company;
    
    
    @Transient
    private List<Tag> tags;
    

    
    
    
    public void addRecoTag(Tag tag) {
    	HibernateUtil.getSession().saveOrUpdate(tag);
    	HibernateUtil.getSession().saveOrUpdate(new RecoTag(this.getId(),tag.getId()));
    }
    
    
    
    public void addAutomaticTags() {
    	addTags(this.tags);
    }
    
    public void addTags(List<Tag> tags) {
    	if(tags != null && !tags.isEmpty()) {
    		for(Tag tag : tags) {
    			addRecoTag(tag);
    		}
    	}
    }
    
    public void removeRecoTag(Tag tag) {
    	HibernateUtil.getSession().delete(new RecoTag(this.getId(),tag.getId()));
    }
    
    
    @Transient
    Function<RecoTag,Tag> recoTagToTags = new Function<RecoTag,Tag>() {

		@Override
		public Tag apply(RecoTag recoTag) {
			return recoTag.getTag();
		}
    	
    };
    
    public List<Tag> getTags() {
    	
    	if(this.tags == null) {
    		List<RecoTag> recoTags = HibernateUtil.getSession().createCriteria(RecoTag.class).add(
        			Restrictions.eq("recommendationId",this.getId())).list();
    	
    		 this.tags = recoTags.stream().map(recoTagToTags).collect(Collectors.<Tag>toList());
    	} 
    	
    	return this.tags;
    	
    }
}
