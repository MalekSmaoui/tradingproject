package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entity.Likee;


public interface LikeeRepository extends JpaRepository<Likee, Integer> {

}
