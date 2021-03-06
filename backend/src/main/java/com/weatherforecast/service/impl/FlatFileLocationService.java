package com.weatherforecast.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.weatherforecast.model.Location;
import com.weatherforecast.service.LocationService;

/**
 * Service to load the cities covered from the configuration files
 */
@Service
public class FlatFileLocationService implements LocationService {
	private static final String DELIMITER = "\\,";

	/**
	 * Injected city list configuration from externalized configuration file. City
	 * list should be '|' delimited string.
	 */
	@Value("${weather.cities}")
	private String cityListConfig;

	FlatFileLocationService () {}
	
	public FlatFileLocationService (String cityListConfig) {
		this.cityListConfig = cityListConfig;
	}
	
	/**
	 * Get list of cities from configuration.
	 */
	@Override
	public List<Location> getLocations() {
		if (cityListConfig == null) {
			return Collections.emptyList();
		}

		List<Location> locations = Arrays.asList(cityListConfig.split(DELIMITER)).stream()
		        .map(locName -> new Location(locName))
		        .collect(Collectors.toList());
		
		return locations;
	}

}
