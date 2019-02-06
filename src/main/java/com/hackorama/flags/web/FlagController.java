package com.hackorama.flags.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hackorama.flags.data.Country;
import com.hackorama.flags.repositories.ContinentRepository;
import com.hackorama.flags.repositories.CountryRepository;

/**
 * The controller for flag API
 * 
 * @author Kishan Thomas (kishan.thomas@gmail.com)
 *
 */
@RestController
public class FlagController {

	@Autowired
	private ContinentRepository continentRepository;
	@Autowired
	private CountryRepository countryRepository;

	@RequestMapping(value = "/flags", method = RequestMethod.GET)
	@ResponseBody
	private ResponseEntity<List<Country>> getFlags() {
		return getFlags(null);
	}

	@RequestMapping(value = "/flags/{id}", method = RequestMethod.GET)
	@ResponseBody
	private ResponseEntity<List<Country>> getFlags(@PathVariable("id") String id) {
		List<Country> countryFlags = new ArrayList<>();
		if (id == null) {
			countryRepository.findAll().forEach(countryFlags::add);
			return ResponseEntity.ok(countryFlags);
		} else {
			if (countryRepository.existsById(id)) {
				countryFlags.add(countryRepository.findById(id).orElse(null));
				return ResponseEntity.ok(countryFlags);
			} else if (continentRepository.existsById(id)) {
				continentRepository.findById(id).orElse(null).getCountries().forEach(country -> {
					countryFlags.add(countryRepository.findById(country.getName()).orElse(null));
				});
				return ResponseEntity.ok(countryFlags);
			}
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(countryFlags);
	}

}
