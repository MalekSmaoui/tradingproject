package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entity.BestPractice;
import tn.esprit.spring.entity.Spread;
import tn.esprit.spring.entity.currencyState;
import tn.esprit.spring.entity.recommendation;


public interface CurrencyStateService {
	public void addCurrencyState(currencyState c);
	public void deleteCurrencyState(int idc);
	public List<currencyState> CurrencyStates();
	public List<currencyState> search(String keyword);
}
