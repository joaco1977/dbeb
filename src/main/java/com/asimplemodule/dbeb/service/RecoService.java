package com.asimplemodule.dbeb.service;

import java.util.List;

import org.hibernate.SQLQuery;

import com.asimplemodule.dbeb.models.Recommendation;
import com.asimplemodule.dbeb.util.HibernateUtil;


public class RecoService {

	
	private RecoService() {}
	
	private static RecoService recoService;
	
	public static RecoService getInstance() {
		if(recoService == null) {
			synchronized (RecoService.class){
                if (null == recoService) {
                	recoService = new RecoService();
                }
			}
		}
		return recoService;
	}
	
	
	private static final String RECO_QUERY = "SELECT recommendations.* "
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
			+ "limit :size offset :from";
	
	
	public List<Recommendation> findRecoByText(String searchText,int from,
			int size) {
		SQLQuery query = HibernateUtil.getSession().createSQLQuery(
				RECO_QUERY);
		
		query.addEntity(Recommendation.class);
		query.setParameter("searchText", "%"+searchText+"%");
		query.setParameter("from", from);
		query.setParameter("size", size);
		
		return query.list();
		
	}
	
	
}
