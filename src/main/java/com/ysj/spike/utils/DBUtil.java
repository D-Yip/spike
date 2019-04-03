package com.ysj.spike.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBUtil {
	
	private static Properties props;
	
	static {
		try {
			InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("application.yml");
			props = new Properties();
			props.load(in);
			in.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConn() throws Exception{
		String url = "jdbc:mysql://192.168.92.103:3306/spike?useUnicode=true&characterEncoding=utf8";
		String username = "root";
		String password = "694975";
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		return DriverManager.getConnection(url,username, password);
	}
}
