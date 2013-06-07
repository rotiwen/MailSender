package com.ipensee;

import java.util.HashMap;

import com.ipensee.mail.MailSenderInfo;

public class EmailInfo {
	private static MailSenderInfo mailInfo = null;
	
	public static MailSenderInfo getMailInfo() {
		if (mailInfo == null) {
			HashMap<String, String> config = ConfigXML.getConfig("config/config-email.xml");
			
			String host = (String) config.get("host");
			String port = (String) config.get("port");
			String user = (String) config.get("user");
			String password = (String) config.get("password");
			String address = (String) config.get("address");
			
			mailInfo = new MailSenderInfo();
			mailInfo.setMailServerHost(host);
			mailInfo.setMailServerPort(port);
			mailInfo.setValidate(true);
			mailInfo.setUserName(user);
			mailInfo.setPassword(password);
			mailInfo.setFromAddress(address);
		}
		
		return mailInfo;
	}
}
