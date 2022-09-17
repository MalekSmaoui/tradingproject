package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.BestPractice;
import tn.esprit.spring.entity.Spread;
import tn.esprit.spring.entity.currencyState;
import tn.esprit.spring.entity.recommendation;
import tn.esprit.spring.repositories.BestPracticeRepsoitory;
import tn.esprit.spring.repositories.CurrencyStateRepository;
import tn.esprit.spring.repositories.RecommendationRepository;
import tn.esprit.spring.repositories.SpreadRepository;



@Service
public class CurrencyStateServiceImpl implements CurrencyStateService{
@Autowired
CurrencyStateRepository currencyStateRepository;


	@Override
	public void addCurrencyState(currencyState c,String currency) {

		if(currencyStateRepository.existsCarLikeCustomQuery(currency))
		{
			currencyState r= currencyStateRepository.searchh(currency);
			r.setState(c.getState());
			currencyStateRepository.save(r);
		}
		else
		{
		currencyStateRepository.save(c);
		}
	}

	
	@Override
	public List<currencyState> CurrencyStates() {
		// TODO Auto-generated method stub
		return currencyStateRepository.findAll();
	}
	


	@Override
	public void deleteCurrencyState(int idc) {
		currencyStateRepository.deleteById(idc);
		
	}


	@Override
	public List<currencyState> search(String keyword) {
		// TODO Auto-generated method stub
		return currencyStateRepository.search(keyword);
	}

}
