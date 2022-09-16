package tn.esprit.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Event;



@Repository
public interface EventRepository extends JpaRepository<Event, Integer>{
	@Query("SELECT b FROM Event b WHERE b.titre LIKE %?1%"
            + " OR b.description LIKE %?1%"		
            )
    public List<Event> search(String keyword);
}
