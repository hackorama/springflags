package com.hackorama.flags.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hackorama.flags.data.Country;

/**
 * The Country data repository 
 * 
 * @author Kishan Thomas (kishan.thomas@gmail.com)
 *
 */
@Repository
public interface CountryRepository extends CrudRepository<Country, String>  {

}
