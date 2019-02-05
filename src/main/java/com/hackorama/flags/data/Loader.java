package com.hackorama.flags.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Load data from data file 
 * 
 * @author Kishan Thomas (kishan.thomas@gmail.com)
 *
 */
public class Loader {
	
	private static final Logger logger = LoggerFactory.getLogger(Loader.class);
	
	public static List<Continent> load(String dataFile) throws IOException {
		logger.info("Loading data from {} ...", dataFile);
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Continent>> typeReference = new TypeReference<List<Continent>>(){};
		try (InputStream inputStream = TypeReference.class.getResourceAsStream(dataFile)) {
			return mapper.readValue(inputStream, typeReference);
		}
	}

}
