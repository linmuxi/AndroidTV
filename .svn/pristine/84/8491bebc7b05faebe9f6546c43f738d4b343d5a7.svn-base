/*
 * Copyright 2015 the original author or phly.
 * 鏈粡姝ｅ紡涔﹂潰鍚屾剰锛屽叾浠栦换浣曚釜浜恒�鍥綋涓嶅緱浣跨敤銆佸鍒躲�淇敼鎴栧彂甯冩湰杞欢.
 */
package com.jxjr.tv;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
	private static final Properties prop = new Properties();
	private static final PropertiesUtil util = new PropertiesUtil();

	private PropertiesUtil() {
		try {
			prop.load(PropertiesUtil.class.getClassLoader()
					.getResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static PropertiesUtil getInstance() {
		return util;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		return prop.getProperty(key);
	}
}
