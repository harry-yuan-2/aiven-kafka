package com.everest.kafka.dao;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDao extends JdbcDaoSupport {
 
    @Autowired 
    DataSource dataSource;
 
	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	public void insert(String message) {
	     String sql = "INSERT INTO MESSAGES " +
	 "(BODY) VALUES (?)" ;
	     getJdbcTemplate().update(sql, new Object[]{
	     message 
	     });
	 }
}