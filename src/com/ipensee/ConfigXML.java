package com.ipensee;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigXML {
	private static final Logger logger = LoggerFactory.getLogger(ConfigXML.class);

	public static HashMap<String, String> getConfig(String xml) {
		SAXBuilder sax = new SAXBuilder();
		HashMap<String, String> config = new HashMap<String, String>();

		try {
			Document doc = sax.build(xml);

			List<Element> childList = doc.getRootElement().getChildren();

			Iterator<Element> listIt = childList.iterator();

			while (listIt.hasNext()) {
				Element element = listIt.next();

				config.put(element.getName(), element.getValue());
			}
		} catch (JDOMException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

		return config;
	}
}
