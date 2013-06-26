package com.ipensee.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.ipensee.DBConnection;

public class CustomerList {
	private static final String sql =
		"SELECT account.ID, account.TrueName, account.Email FROM IM_valid_email AS email LEFT OUTER JOIN IM_Account AS account ON account.id = email.account_id LEFT OUTER JOIN IM_mail_sender_config AS config ON config.account_id = account.ID WHERE email.is_valid = 1 AND email.enable = 1 AND IFNULL(account.Email, '') <> '' AND config.sender_time = ?";
//	private static final String sql =
//		"SELECT account.ID, account.TrueName, email.Email FROM IM_valid_email AS email LEFT OUTER JOIN IM_Account AS account ON account.id = email.account_id LEFT OUTER JOIN IM_mail_sender_config AS config ON config.account_id = account.ID WHERE email.is_valid = 1 AND email.enable = 1 AND IFNULL(account.Email, '') <> '' AND config.sender_time = ?";
	
	public static List<Customer> getCustomerList() throws ClassNotFoundException, SQLException {
		List<Customer> customerList = new ArrayList<Customer>();
		
		Connection conn = DBConnection.getConnection();

		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setInt(1, Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		
		ResultSet result = stmt.executeQuery();
		
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
