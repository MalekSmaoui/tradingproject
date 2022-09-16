package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.BestPractice;
import tn.esprit.spring.entity.Spread;
import tn.esprit.spring.entity.recommendation;
import tn.esprit.spring.repositories.BestPracticeRepsoitory;
import tn.esprit.spring.repositories.RecommendationRepository;
import tn.esprit.spring.repositories.SpreadRepository;



@Service
public class SpreadServiceImpl implements SpreadService{
@Autowired
SpreadRepository spreadRepository;


	@Override
	public void addSpread(Spread s) {

		spreadRepository.save(s);
		
	}

	
	@Override
	public List<Spread> Spreads() {
		// TODO Auto-generated method stub
		return spreadRepository.findAll();
	}
	


	@Override
	public void deleteSpread(int ids) {
		spreadRepository.deleteById(ids);
		
	}


	@Override
	public List<Spread> search(String keyword) {
		// TODO Auto-generated method stub
		return spreadRepository.search(keyword);
	}

}
