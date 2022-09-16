package tn.esprit.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entity.BestPractice;
import tn.esprit.spring.entity.Spread;


public interface SpreadRepository extends JpaRepository<Spread, Integer> {
	 @Query("SELECT b FROM Spread b WHERE currencyPair LIKE %?1%"
	            )
	    public List<Spread> search(String keyword);
	 
	 @Query("select case when count(c)> 0 then true else false end from Spread c where lower(c.currencyPair) like lower(:model)")
	 boolean existsCarLikeCustomQuery(@Param("model") String currencyPair);
	 
	 @Query("SELECT b FROM Spread b WHERE currencyPair = :keyword"
	            )
	    public Spread searchh(String keyword);
}
