package com.hackorama.flags.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackorama.flags.repositories.ContinentRepository;
import com.hackorama.flags.repositories.CountryRepository;

/**
 * Load data from data file and save them to the data repositories
 * 
 * @author Kishan Thomas (kishan.thomas@gmail.com)
 *
 */
@Component
public class Loader implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(Loader.class);

	private ContinentRepository continentRepository;
	private CountryRepository countryRepository;

	@Value("${data.file}")
	private String dataFilePath;
	
	public Loader() {
	}

	/**
	 * Initialize the Loader with repositories
	 * 
	 * @param continentRepository
	 *            The continent repository
	 * @param countryRepository
	 *            The country repository
	 */
	@Autowired
	public Loader(ContinentRepository continentRepository, CountryRepository countryRepository) {
		this.continentRepository = continentRepository;
		this.countryRepository = countryRepository;
	}

	/**
	 * Load the data from a JSON data file
	 * 
	 * @param dataFile
	 *            The data file to load from
	 * @return List of continents data
	 * @throws IOException
	 *             On error reading the data file
	 */
	public List<Continent> load(String dataFile) throws IOException {
		logger.info("Loading data from {} ...", dataFile);
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Continent>> typeReference = new TypeReference<List<Continent>>() {
		};
		try (InputStream inputStream = TypeReference.class.getResourceAsStream(dataFile)) {
			return mapper.readValue(inputStream, typeReference);
		}
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<Continent> continents = load(dataFilePath);
		continents.forEach(continent -> {
			continentRepository.save(continent);
			continent.getCountries().forEach(country -> {
				countryRepository.save(country);
			});
		});
	}

}
