package com.newgen.util;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

public class LoggerManager {

	public void createLogFile() {
		try {
			Date date = new Date();
			DateFormat logDateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String dynamicLog = "Log/" + logDateFormat.format(date) + "/SMSAutoServer.xml";

			Properties p = new Properties();
			p.load(new FileInputStream("log4j.properties"));
			String orgFileName = p.getProperty("log4j.appender.mLogger.File");

			if (!(orgFileName == null || orgFileName.equalsIgnoreCase(""))) {
				dynamicLog = "Log/" + logDateFormat.format(date) + orgFileName.substring(orgFileName.lastIndexOf("/"));
			}

			File d = new File("Log/" + logDateFormat.format(date));
			d.mkdirs();
			File fl = new File(dynamicLog);
			if (!fl.exists())
				fl.createNewFile();

			p.put("log4j.appender.mLogger.File", dynamicLog); // overwrite "log.dir"
			PropertyConfigurator.configure(p);
		} catch (Exception e) {
			SMSAutoService.mLogger.info("Eexception in creating dynamic log :" + e);
			SMSAutoService.mLogger.error("Eexception in creating dynamic log :" + e);
		}
	}

	public void insertSMSLog(String sWORKITEM_NO, String sInputXML, String SMSResponse, String ResponseCode,
			String sessionID) {

		SMSAutoService.mLogger.info("Starting insertSMSLog method...");
		

		try {
			String tableName = "NG_INFOBIP_AUDIT_LOG";
			String columnName = "(WORKITEM_NO, INPUT_XML, OUTPUT_XML, RESPONSE_CODE)";
			String values = "('" + sWORKITEM_NO + "','" + sInputXML + "','" + SMSResponse + "','" + ResponseCode + "')";
			String cabinetName = SMSAutoService.cabinetName;

			SMSAutoService.mLogger.info("Preparing to generate input XML for DB insert...");
			SMSAutoService.mLogger.info("Table: " + tableName + ", Columns: " + columnName + ", Values: " + values);

			GenerateXml xml = new GenerateXml();
			String inputXml = xml.ExecuteQuery_APInsert(tableName, columnName, values, cabinetName, sessionID);

			SMSAutoService.mLogger.info("Generated input XML: " + inputXml);

			SMSAutoService.mLogger.info("Calling NgEjbApiCall.executeNgApi...");
			String outputXML = NgEjbApiCall.executeNgApi(inputXml);

			SMSAutoService.mLogger.info("Received output XML: " + outputXML);
			SMSAutoService.mLogger.info("insertSMSLog method executed successfully.");

		} catch (Exception e) {
			SMSAutoService.mLogger.error("Exception occurred in insertSMSLog: " + e.getMessage(), e);
		}
	}

}
