package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entity.BestPractice;
import tn.esprit.spring.entity.Spread;
import tn.esprit.spring.entity.recommendation;


public interface SpreadService {
	public void addSpread(Spread s,String currencyPair);
	public void deleteSpread(int ids);
	public List<Spread> Spreads();
	public List<Spread> search(String keyword);
}
