package com.dbeb.db;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.asimplemodule.dbeb.models.Company;
import com.asimplemodule.dbeb.models.Location;
import com.asimplemodule.dbeb.models.Recommendation;

public class RecommendationTest extends AbstractTest {

	
	
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
		reco.setCreateUserEmail("jaoquincabal@gmail.com");
		reco.setActive(true);
		reco.setVotes(3);
		
		session.save(reco);
		
		
		Assert.assertTrue(reco.getId() != 0);
	}
	
}
