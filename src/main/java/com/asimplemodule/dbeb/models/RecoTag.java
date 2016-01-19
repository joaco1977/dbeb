package com.asimplemodule.dbeb.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.asimplemodule.dbeb.util.HibernateUtil;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recommendations_tags")
public class RecoTag implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 5845356171572151640L;

	@Id
    private long recommendationId;
    
    @Id
    private long tagId;
    
   
    public Tag getTag() {
    	return (Tag)HibernateUtil.getSession().get(Tag.class, this.tagId);
    }
    
    public Recommendation getRecommendation() {
    	return (Recommendation)HibernateUtil.getSession().get(Recommendation.class, this.getRecommendationId());
    }
}
