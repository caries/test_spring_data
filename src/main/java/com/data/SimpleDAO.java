package com.data;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Executes simple query.
 */
public class SimpleDAO extends JdbcDaoSupport {
    public void updateDatabase() {
        getJdbcTemplate().execute("INSERT INTO test (c) VALUES (111);");
        System.out.println("Updated the database");
    }
}