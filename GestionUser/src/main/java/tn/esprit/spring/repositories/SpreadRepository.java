package tn.esprit.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.entity.BestPractice;
import tn.esprit.spring.entity.Spread;


public interface SpreadRepository extends JpaRepository<Spread, Integer> {
	 @Query("SELECT b FROM Spread b WHERE currencyPair LIKE %?1%"
	            )
	    public List<Spread> search(String keyword);
}
