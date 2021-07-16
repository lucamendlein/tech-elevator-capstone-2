package com.techelevator.model.jdbc;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.*;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import com.techelevator.model.VenueDAO;
import com.techelevator.model.Venue;
import java.util.List;

public class VenueDAOIntegrationTest extends DAOIntegrationTest {

	/*
	 * Using this particular implementation of DataSource so that every database
	 * interaction is part of the same database session and hence the same database
	 * transaction
	 *

	 */

	private JdbcTemplate jdbcTemplate;
	private VenueDAO venueDAO;

	@Before
	public void setupBeforeTest() {
		venueDAO = new JDBCVenueDAO(getDataSource());
		jdbcTemplate = new JdbcTemplate(getDataSource());
	}

	/**
	TESTING SELECT with multiple objects being returned
	 **/
	@Test
	public void retrieve_multiple_venues() {
		//Arrange
		//Get a count of number of values in the table
		List<Venue> venueList = venueDAO.getAllVenues();

		//insert multiple venues
		Venue venueOne = getVenue(50, "New Venue",  4, "what a cool new venue");
		Venue venueTwo = getVenue(67, "Second New Venue",  2, "wowow even cooler new venue");
		venueDAO.create(venueOne);
		venueDAO.create(venueTwo);

		//Act
		List<Venue> venuesFromDatabase = venueDAO.getAllVenues();

		//Assert
		Assert.assertEquals((venueList.size() + 2), venuesFromDatabase.size());

	}


	private Venue getVenue(int id, String venue_name, int city_id, String description) {
		Venue venue = new Venue();
		venue.setId(id);
		venue.setName(venue_name);
		venue.setCity_id(city_id);
		venue.setDescription(description);

		return venue;
	}
}
