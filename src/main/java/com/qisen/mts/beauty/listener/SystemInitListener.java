package com.qisen.mts.beauty.listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;

import com.qisen.mts.common.model.constant.ConfigConsts;

import net.rubyeye.xmemcached.MemcachedClient;

public class SystemInitListener implements ServletContextListener {

	private static final Logger logger = LoggerFactory.getLogger(SystemInitListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		logger.info("System init start >>>");
		MemcachedClient memcachedClient = ContextLoader.getCurrentWebApplicationContext().getBean(MemcachedClient.class);
		logger.info("Read system required json data start >>>");
		try {
			String folderPath = getClass().getResource("/json/").getFile();
			File folder = new File(folderPath);
			if (folder.exists() && folder.isDirectory()) {
				File jsonFiles[] = folder.listFiles();
				if (jsonFiles != null) {
					for (File file : jsonFiles) {
						FileInputStream inputStream = new FileInputStream(file);
						InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
						BufferedReader bufferedReader = null;
						try {
							bufferedReader = new BufferedReader(inputStreamReader);
							StringBuffer strBuffer = new StringBuffer();
							String tempStr = null;
							while ((tempStr = bufferedReader.readLine()) != null)
								strBuffer.append(tempStr);

							bufferedReader.close();
							if (StringUtils.isNotBlank(strBuffer)) {
								String fileName = file.getName();
								if (!memcachedClient.add(ConfigConsts.JSON_DATA + fileName, 0, strBuffer.toString()))
									memcachedClient.replace(ConfigConsts.JSON_DATA + fileName, 0, strBuffer.toString());
								logger.info("Read required json data " + fileName + ", done !!");
							}
						} finally {
							if (bufferedReader != null)
								bufferedReader.close();
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("Read report json data error", e);
		}
		logger.info("Read system required finished <<<");
		logger.info("System init finished <<<");
	}

}
