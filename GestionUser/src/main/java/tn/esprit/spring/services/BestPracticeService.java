package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entity.BestPractice;


public interface BestPracticeService {
	public void addBestPractice(BestPractice bp);
	public void EditBestPractice(BestPractice bp,int idbp);
	public void deleteBestPractice(int idbp);
	public List<BestPractice> BestPractices(int idbp);
	public void validateBestPractice(int idbp);
	public BestPractice visitBestPractice(int idBestPractice);
	public List<BestPractice> BestPractices();
	public List<BestPractice> search(String keyword);
}
