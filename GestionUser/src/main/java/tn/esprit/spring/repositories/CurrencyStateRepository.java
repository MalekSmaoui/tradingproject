package tn.esprit.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entity.BestPractice;
import tn.esprit.spring.entity.Spread;
import tn.esprit.spring.entity.currencyState;


public interface CurrencyStateRepository extends JpaRepository<currencyState, Integer> {
	 @Query("SELECT b FROM currencyState b WHERE b.state LIKE %?1%"
	            + " OR b.currency LIKE %?1%"
	            )
	    public List<currencyState> search(String keyword);
		 
		 @Query("select case when count(c)> 0 then true else false end from currencyState c where lower(c.currency) like lower(:model)")
		 boolean existsCarLikeCustomQuery(@Param("model") String currency);
	    
	    @Query("SELECT b FROM currencyState b WHERE currency = :keyword"
		            )
		    public currencyState searchh(String keyword);
}
