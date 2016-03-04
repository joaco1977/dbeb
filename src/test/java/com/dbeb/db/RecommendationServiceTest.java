package com.dbeb.db;

import org.junit.Assert;
import org.junit.Test;

import com.asimplemodule.dbeb.service.RecoService;

public class RecommendationServiceTest extends AbstractTest {

	
	
	
	
	
	@Test
	public void shouldReturnRecommendationsByParams() {
		
		String searchText = "%Buenos Aires%";
		
		
		
		Assert.assertTrue(!RecoService.getInstance().findRecoByText(searchText, 0, 4).isEmpty());
		
	}
	
}
