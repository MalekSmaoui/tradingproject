package tn.esprit.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.entity.BestPractice;
import tn.esprit.spring.entity.currencyState;


public interface CurrencyStateRepository extends JpaRepository<currencyState, Integer> {
	 @Query("SELECT b FROM currencyState b WHERE b.state LIKE %?1%"
	            + " OR b.currency LIKE %?1%"
	            )
	    public List<currencyState> search(String keyword);
}
