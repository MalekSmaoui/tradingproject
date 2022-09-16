package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.BestPractice;
import tn.esprit.spring.repositories.BestPracticeRepsoitory;



@Service
public class BestPracticeServiceImpl implements BestPracticeService{
@Autowired
BestPracticeRepsoitory bestPracticeRepsoitory;


	@Override
	public void addBestPractice(BestPractice bp) {
		bp.setValid(false);
		bestPracticeRepsoitory.save(bp);
		
	}
	@Override
	public BestPractice visitBestPractice(int idBestPractice) {
		
		return bestPracticeRepsoitory.findById(idBestPractice).orElse(null);
	}
	@Override
	public List<BestPractice> BestPractices() {
		// TODO Auto-generated method stub
		return bestPracticeRepsoitory.findAll();
	}
	@Override
	public void EditBestPractice(BestPractice bp, int idbp) {
		BestPractice b= bestPracticeRepsoitory.findById(idbp).orElse(null);
		b.setDateCreation(bp.getDateCreation());
		b.setDescription(bp.getDescription());
		b.setPieceJointe(bp.getPieceJointe());
		b.setTitre(bp.getTitre());
		bestPracticeRepsoitory.save(b);
	}

	@Override
	public void deleteBestPractice(int idbp) {
	bestPracticeRepsoitory.deleteById(idbp);
		
	}

	@Override
	public List<BestPractice> BestPractices(int idbp) {
		// TODO Auto-generated method stub
		return bestPracticeRepsoitory.findAll();
	}

	@Override
	public void validateBestPractice(int idbp) {
		BestPractice b= bestPracticeRepsoitory.findById(idbp).orElse(null);
       b.setValid(true);		
      bestPracticeRepsoitory.save(b);
	}

	@Override
	public List<BestPractice> search(String keyword) {
		// TODO Auto-generated method stub
		return bestPracticeRepsoitory.search(keyword);
	}

}
