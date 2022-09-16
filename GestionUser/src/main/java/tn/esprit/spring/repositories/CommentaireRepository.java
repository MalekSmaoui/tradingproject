package tn.esprit.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Commentaire;



@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {
@Query(
		"SELECT c FROM commentaire c  WHERE event_id_event =?1 ORDER BY nbrlikes DESC" )
public List<Commentaire> commentaires(@Param("id") int eventId );

}