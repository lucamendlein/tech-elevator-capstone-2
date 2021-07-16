package com.techelevator.model.jdbc;

import com.techelevator.model.Space;
import com.techelevator.model.SpaceDAO;
import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public abstract class SpaceDAOIntegrationTest {

	/*
	 * Using this particular implementation of DataSource so that every database
	 * interaction is part of the same database session and hence the same database
	 * transaction
	 */
	private static SingleConnectionDataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private SpaceDAO spaceDAO;

	/*
	 * Before any tests are run, this method initializes the datasource for testing.
	 */
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/excelsior_venues");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		/*
		 * The following line disables autocommit for connections returned by this
		 * DataSource. This allows us to rollback any changes after each test
		 */
		dataSource.setAutoCommit(false);
	}

	/*
	 * After all tests have finished running, this method will close the DataSource
	 */
	@AfterClass
	public static void closeDataSource() {
		dataSource.destroy();
	}

	/*
	 * After each test, we rollback any changes that were made to the database so
	 * that everything is clean for the next test
	 */
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	/*
	 * This method provides access to the DataSource for subclasses so that they can
	 * instantiate a DAO for testing
	 */
	protected DataSource getDataSource() {
		return dataSource;
	}

	@Before
	public void setupBeforeTest() {
		spaceDAO = new JDBCSpaceDAO(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	TESTING SELECT with multiple objects being returned
	 **/
	@Test
	public void retrieve_multiple_spaces() {
		//Arrange
		//Get a count of number of values in the table
		List<Space> spaceList = spaceDAO.getAllSpaces();
	}

}
