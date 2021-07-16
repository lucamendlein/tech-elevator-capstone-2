package com.techelevator.model.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;

public class JDBCVenueDAOTest {

    private static SingleConnectionDataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    public JDBCVenueDAOTest(DataSource dataSource) {

    };
}
