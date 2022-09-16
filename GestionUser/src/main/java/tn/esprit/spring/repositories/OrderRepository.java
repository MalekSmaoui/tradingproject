package tn.esprit.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entity.BestPractice;
import tn.esprit.spring.entity.Order;
import tn.esprit.spring.entity.User;


public interface OrderRepository extends JpaRepository<Order, Integer> {
	 @Query("SELECT b FROM Order b WHERE b.orderType LIKE %?1%"
			    + " OR b.currencyPair LIKE %?1%"
	            )
	    public List<Order> search(String keyword);
	 	
	 @Query("SELECT b FROM Order b WHERE b.creator.idUser = :id")

		 public List<Order> findAllByCreatorId(@Param("id") int id);
	 
	 @Query("SELECT b FROM Order b WHERE b.orderType = :keyword"
			    + " AND b.creator.idUser = :id"
	            )
	    public List<Order> search2(@Param("id") int id,@Param("keyword") String keyword);
	 
	 @Transactional
	 @Modifying
	 @Query("delete from Order b WHERE b.id = :id"
		
	            )
	 public void deleteorder(@Param("id") int id);
}
