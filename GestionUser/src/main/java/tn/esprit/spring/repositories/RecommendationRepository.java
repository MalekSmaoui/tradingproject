package tn.esprit.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.entity.BestPractice;
import tn.esprit.spring.entity.recommendation;


public interface RecommendationRepository extends JpaRepository<recommendation, Integer> {
	 @Query("SELECT b FROM recommendation b WHERE b.currencyPair LIKE %?1%"
	            + " OR b.comment LIKE %?1%"
	            )
	    public List<recommendation> search(String keyword);
}
