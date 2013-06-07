package com.ipensee.main;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ipensee.EmailInfo;
import com.ipensee.LogbackConfigLoader;
import com.ipensee.entity.Customer;
import com.ipensee.entity.CustomerList;
import com.ipensee.mail.MailSenderInfo;

public class Executor {
//	private static final Logger logger = LoggerFactory.getLogger(Executor.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();
		
		LogbackConfigLoader.load("config/logback.xml");
		
		Logger logger = LoggerFactory.getLogger(Executor.class);

		try {
			MailSenderInfo mailInfo = EmailInfo.getMailInfo();
			List<Customer> customerList = CustomerList.getCustomerList();
			IPenseeMailSender mailSender = new IPenseeMailSender(mailInfo, customerList);
			mailSender.sendEmailToCustomer();
			logger.info("发生成功！请查看各自的 Email。");
		}
		catch (Exception e) {
			logger.info(e.getMessage());
		}
		
		long t2 = System.currentTimeMillis();
		
		logger.info("执行时间：" + (t2 - t1) / 1000 + "秒。");
		logger.info("---------------------------------");
	}

}
