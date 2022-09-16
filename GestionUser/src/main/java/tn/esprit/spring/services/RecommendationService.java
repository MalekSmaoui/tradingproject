package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entity.BestPractice;
import tn.esprit.spring.entity.recommendation;


public interface RecommendationService {
	public void addRecommendation(recommendation r);
	public void deleteRecommendation(int idr);
	public List<recommendation> Recommendations();
	public List<recommendation> search(String keyword);
}
