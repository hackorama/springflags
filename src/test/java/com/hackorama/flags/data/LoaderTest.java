package com.hackorama.flags.data;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

/**
 * Test the data loading
 * 
 * @author Kishan Thomas (kishan.thomas@gmail.com)
 *
 */
public class LoaderTest {

	@Test
	public void onLoadingJson_expectsCorrectData() throws IOException {
		List<Continent> continents = Loader.load("/continents.txt");
		testFlag(continents, "Africa", "Nigeria", "🇳🇬");
		testFlag(continents, "America", "USA", "🇺🇸");
		testFlag(continents, "Oceania", "Solomon Islands", "🇸🇧");
		assertEquals("Test expected number of continents loaded", 5, continents.size());
	}

	private void testFlag(List<Continent> continents, String continent, String country, String flag) {
		assertEquals("Expected country flag is loaded and mapped", continents.stream()
				.filter(c -> continent.equals(c.getContinent())).findFirst().orElse(null).getCountry(country).getFlag(),
				flag);
	}

}
