package com.newgen.util;

import com.newgen.omni.wf.util.app.NGEjbClient;
import com.newgen.omni.wf.util.excp.NGException;

public class NgEjbApiCall {

	private static NGEjbClient ngEjbClientConnection;

	static {
		try {
			SMSAutoService.mLogger.info("Initializing NGEjbClient shared instance...");
			ngEjbClientConnection = NGEjbClient.getSharedInstance();
			SMSAutoService.mLogger.info("NGEjbClient shared instance initialized successfully.");
		} catch (NGException e) {
			SMSAutoService.mLogger.error("Exception while initializing NGEjbClient shared instance: " + e.getMessage(), e);
			e.printStackTrace();
		}
	}

	public NgEjbApiCall() {

	}

	public static String executeNgApi(String ipXML) {
		SMSAutoService.mLogger.info("NgEjbApiCall : executeNgApi method called.");
		String out = "";
		try {
			SMSAutoService.mLogger.info("Calling ngEjbClientConnection.makeCall with provided input XML...");
			out = ngEjbClientConnection.makeCall(SMSAutoService.jtsIP, SMSAutoService.jtsPort, "WebSphere", ipXML);
			SMSAutoService.mLogger.info("Received response from ngEjbClientConnection.makeCall.");
		} catch (Exception e) {
			SMSAutoService.mLogger.error("NgEjbApiCall : executeNgApi Exception : " + e.getMessage(), e);
		}
		return out;
	}

}
