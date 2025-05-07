if (SMSResponse.equalsIgnoreCase(SuccessResponseCode)) {
    mLogger.info("SMS sent successfully for WorkItem: " + sWORKITEM_NO);
    mLogger.info("Updating status to SuccessCode: " + SuccessCode + " for condition: " + whereCondition);
    updateStatus("'" + SuccessCode + "'", whereCondition);
    
    ResponseCode = SuccessCode;

    mLogger.info("Inserting SMS log with SuccessCode...");
    loggerManager.insertSMSLog(sWORKITEM_NO, sInputXML, SMSResponse, ResponseCode, sessionID);

} else {
    mLogger.info("SMS failed for WorkItem: " + sWORKITEM_NO);
    mLogger.info("Updating status to FailCode: " + FailCode + " for condition: " + whereCondition);
    updateStatus("'" + FailCode + "'", whereCondition);
    
    ResponseCode = FailCode;

    mLogger.info("Inserting SMS log with FailCode...");
    loggerManager.insertSMSLog(sWORKITEM_NO, sInputXML, SMSResponse, ResponseCode, sessionID);
}
