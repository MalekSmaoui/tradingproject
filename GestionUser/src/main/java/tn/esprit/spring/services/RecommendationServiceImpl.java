package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.BestPractice;
import tn.esprit.spring.entity.recommendation;
import tn.esprit.spring.repositories.BestPracticeRepsoitory;
import tn.esprit.spring.repositories.RecommendationRepository;



@Service
public class RecommendationServiceImpl implements RecommendationService{
@Autowired
RecommendationRepository recommendationRepository;


	@Override
	public void addRecommendation(recommendation r) {

		recommendationRepository.save(r);
		
	}

	
	@Override
	public List<recommendation> Recommendations() {
		// TODO Auto-generated method stub
		return recommendationRepository.findAll();
	}
	


	@Override
	public void deleteRecommendation(int idr) {
		recommendationRepository.deleteById(idr);
		
	}


	@Override
	public List<recommendation> search(String keyword) {
		// TODO Auto-generated method stub
		return recommendationRepository.search(keyword);
	}

}
