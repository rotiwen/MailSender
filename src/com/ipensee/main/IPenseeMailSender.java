package com.ipensee.main;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ipensee.entity.Customer;
import com.ipensee.entity.News;
import com.ipensee.mail.MailSenderInfo;
import com.ipensee.mail.SimpleMailSender;
import com.ipensee.webservice.client.WsNews;
import com.ipensee.webservice.client.WsNewsSoap;

public class IPenseeMailSender {
	private static final Logger logger = LoggerFactory.getLogger(IPenseeMailSender.class);
	
	private static final String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	private static final String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	private List<Customer> customerList;
	private MailSenderInfo mailInfo;
	
	private static WsNewsSoap soap = null;
	private static Gson gson = null;
	private static Type type = null;
	
	static {
		soap = new WsNews().getWsNewsSoap();
		gson = new Gson();
		type = new TypeToken<List<News>>(){}.getType();
	}
	
	public IPenseeMailSender(MailSenderInfo mailInfo, List<Customer> customerList) {
		this.mailInfo = mailInfo;
		this.customerList = customerList;
	}

	public void sendEmailToCustomer() {
		Iterator<Customer> it = customerList.iterator();
		
		while (it.hasNext()) {
			Customer customer = it.next();
			
			mailInfo.setToAddress(customer.getEmail());
			mailInfo.setSubject("每日要点（" + today + "）");
			
			String mailContent = getMailContent(customer);
			mailInfo.setContent(mailContent);

//			System.out.println(mailInfo.getContent());
			if (mailContent.isEmpty()) {
				logger.info("由于【" + customer.getName() + "】没有邮件内容，故没有发送！");
			}
			else {
				SimpleMailSender.sendHtmlMail(mailInfo);
			}
		}
	}
	
	private String getMailContent(Customer customer) {
		String jsonNews = soap.getNews(customer.getId(), 0, 10, "", "", "1", "", "2", "1900-01-01 00:00:00", now, "0", 1, "0", "0", "", "", "");
		jsonNews = jsonNews.substring(6, jsonNews.length() - 1);
		
		List<News> list = gson.fromJson(jsonNews, type);
		Iterator<News> it = list.iterator();
		
		logger.info("【" + customer.getName() + "】的新闻数量：" + list.size());
		
		StringBuffer buffer = new StringBuffer();
		
		while (it.hasNext()) {
			News news = it.next();
			if (news.getIsForward().equals("0")) {
				buffer.append("<font color='blue'>" + news.getKeyWords() + "</font>&nbsp;(" + news.getNewTypeName() + ")<br/>");
				buffer.append(news.getUrl().trim().isEmpty() ? news.getNewObject() + "<br/>" : "<a href='" + news.getUrl() + "'>" + news.getNewObject() + "</a><br/>");
				buffer.append(news.getNews() + "<br/>");
				if (!news.getFileName().trim().isEmpty()) {
					buffer.append("附件：<a href='http://192.168.1.201:8001/" + news.getFileOrder().replace("notExist/", "Attach/") + "'>" + news.getFileName() + "</a><br/>");
				}
				buffer.append(news.getEditDate() + "<br/>");
				buffer.append("来源：" + news.getNewFrom() + "<br/>");
				buffer.append(news.getNewAuthor() + "<br/>");
				buffer.append("<hr/>");
			}
		}
		
		return buffer.toString();
	}
	
}
