package com.xzh.weixin.web.main;

import java.io.InputStream;
import java.util.Properties;

public class Config {

	private static Properties props = new Properties();
	static {
		InputStream ins = Thread.currentThread().getContextClassLoader().getResourceAsStream("jetty.properties");
		if(ins!=null)

		{
			try {
				props.load(ins);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					ins.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	public String server_name = props.getProperty("server_name");
	public String deploy_root_home = props.getProperty("deploy_root_home");;

	public String ip = "127.0.0.1";
	public int port = Integer.valueOf(props.getProperty("server_port"));
	public int min_thread = 128;
	public int max_thread = 512;
	public long idle_timeout = 60000L;
	public int accecp_queuesize = 6000;
	public long stop_timeout = 60000L;
	public String server_log_home = deploy_root_home + "/"+ server_name + "/"+ "logs/";
	public String temp_work_path = deploy_root_home + "/"+ server_name + "/"+ "work/";



}
