package com.dbeb.db;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.junit.Assert;
import org.junit.Test;

import com.asimplemodule.dbeb.models.Company;
import com.asimplemodule.dbeb.models.Location;
import com.asimplemodule.dbeb.models.Recommendation;

public class RecommendationPersistenceTest extends AbstractTest {

	
	
	@Test
	public void shouldCreateRecommendation() {
		
		
		Recommendation reco = new Recommendation();
		reco.setName("name");
		
		Location location = new Location();
		location.setFormattedAddress("addressssssssss");
		location.setLatitude(-35.5621548);
		location.setLongitude(-26.5621548);
		
		
		Company company = new Company();
		company.setEmail("joaquincabal@gmail.com");
		company.setFacebook("facebook");
		company.setName("Company");
		
		
		company.setLocation(location);
		
		reco.setCompany(company);
		reco.setDetail("detailsssssssss");
		reco.setRecodate(new Date());
		//reco.setCreateUserEmail("jaoquincabal@gmail.com");
		reco.setActive(true);
		reco.setVotes(3);
		
		session.save(reco);
		
		
		Assert.assertTrue(reco.getId() != 0);
	}
	
	@Test
	public void shouldReturnRecommendationsByParams() {
		
		String searchText = "%Buenos Aires%";
		
		SQLQuery query = session.createSQLQuery("SELECT recommendations.* "
				+ "FROM public.recommendations,public.companies, public.locations, public.users "
				+ "WHERE companies.id = recommendations.companyid AND "
				+ "locations.id = companies.locationid AND "
				+ "users.id = recommendations.createuserid AND "
				+ "(companies.name ilike :searchText OR "
				+ "locations.formattedAddress ilike :searchText OR  "
				+ "recommendations.detail like :searchText OR  "
				+ "users.firstName ilike :searchText OR "
				+ "users.lastName ilike :searchText OR "
				+ "users.facebookId ilike :searchText OR "
				+ "recommendations.id IN ("
				+ "SELECT recommendations_tags.recommendationId "
				+ "FROM public.tags INNER JOIN public.recommendations_tags "
				+ "ON tags.id = recommendations_tags.tagId	"
				+ "WHERE tags.name ilike :searchText))"
				+ "limit :size offset :from");
		
		query.addEntity(Recommendation.class);
		query.setParameter("searchText", searchText);
		query.setParameter("size", 1);
		query.setParameter("from", 0);
		
		
		Assert.assertTrue(!query.list().isEmpty());
		
	}
	
}
