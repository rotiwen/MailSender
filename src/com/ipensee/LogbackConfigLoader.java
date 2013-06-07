package com.ipensee;

import java.io.File;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;

public class LogbackConfigLoader {
	public static void load(String filePath) {
		try {
			LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
			context.reset();
			JoranConfigurator config = new JoranConfigurator();
			config.setContext(context);
			config.doConfigure(new File(filePath));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
