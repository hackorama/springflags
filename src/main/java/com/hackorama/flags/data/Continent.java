package com.hackorama.flags.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

@KeySpace("continents")
public class Continent {
	@Id
	private String continent;

	private List<Country> countries = new ArrayList<>();

	public Continent() {
	}

	public Continent(String continent, List<Country> countries) {
		this.continent = continent;
		this.countries = countries;
	}

	public String getContinent() {
		return continent;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public Country getCountry(String name) {
		return countries.stream().filter(country -> name.equals(country.getName())).findFirst().orElse(null);
	}

}
