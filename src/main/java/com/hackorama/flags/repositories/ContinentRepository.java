package com.hackorama.flags.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hackorama.flags.data.Continent;

@Repository
public interface ContinentRepository extends CrudRepository<Continent, String>  {

}
