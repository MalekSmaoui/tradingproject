package tn.esprit.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.ImageUser;

@Repository
public interface IImageUserRepository extends CrudRepository <ImageUser,Integer>{

}
