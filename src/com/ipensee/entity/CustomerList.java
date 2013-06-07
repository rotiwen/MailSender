package com.ipensee.entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ipensee.DBConnection;

public class CustomerList {
//	private List<Customer> customerList;
	private static final String sql =
		"SELECT account.ID, account.TrueName, account.Email FROM IM_valid_email AS email LEFT OUTER JOIN IM_Account AS account ON account.id = email.account_id WHERE email.is_valid = 1 AND IFNULL(account.Email, '') <> ''";
	
//	private static final String sql =
//		"SELECT account.ID, account.TrueName, email.Email FROM IM_valid_email AS email LEFT OUTER JOIN IM_Account AS account ON account.id = email.account_id WHERE email.is_valid = 1 AND IFNULL(account.Email, '') <> ''";
	
	public static List<Customer> getCustomerList() throws ClassNotFoundException, SQLException {
		List<Customer> customerList = new ArrayList<Customer>();
		
		Connection conn = DBConnection.getConnection();
		
		Statement stmt = conn.createStatement();
		
		ResultSet result = stmt.executeQuery(sql);
		
		while (result.next()) {
			Customer customer = new Customer();
			
			customer.setId(result.getString(1));
			customer.setName(result.getString(2));
			customer.setEmail(result.getString(3));
			
			customerList.add(customer);
		}
		
		return customerList;
	}

}
