package com.asimplemodule.dbeb.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.criterion.Restrictions;

import com.asimplemodule.dbeb.util.HibernateUtil;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Entity
@Table(name = "recommendations_tags")
@IdClass(RecoTag.class)
public class RecoTag {

    @Id
    private long recommendationId;
    
    @Id
    private long tagId;
    
   
    public Tag getTag() {
    	return (Tag)HibernateUtil.getSession().createCriteria(Tag.class).add(Restrictions.eq("tagId", this.getTagId()));
    }
    
    public Recommendation getRecommendation() {
    	return (Recommendation)HibernateUtil.getSession().createCriteria(Recommendation.class).add(
    			Restrictions.eq("recommendationId", this.getRecommendationId()));
    }
}
