if (SMSResponse.equalsIgnoreCase(SuccessResponseCode)) {
						updateStatus("'" + SuccessCode + "'", whereCondition);
						ResponseCode = SuccessCode;
						loggerManager.insertSMSLog(sWORKITEM_NO, sInputXML, SMSResponse, ResponseCode,sessionID);

					} else {
						updateStatus("'" + FailCode + "'", whereCondition);
						ResponseCode = FailCode;
						loggerManager.insertSMSLog(sWORKITEM_NO, sInputXML, SMSResponse, ResponseCode,sessionID);

					}



				mLogger.info("Complete ArrayList Values: " + ArrLstTableValues.toString());



