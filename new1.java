private static String postAPI() {
        String baseUrl = https://sms-interceptor-uat.rakbanktst.ae/sms-receiver/sms/3/text/advanced;

        try {
            // POST Request
            URL postUrl = new URL(baseUrl);
            HttpURLConnection postConnection = (HttpURLConnection) postUrl.openConnection();
            Digital_PL.mLogger.debug("After URL connection:");

            // Set request method and headers
            postConnection.setRequestMethod("POST");
            postConnection.setRequestProperty("Content-Type", "application/json");
            postConnection.setRequestProperty("source", "application/json");
            postConnection.setDoOutput(true);

            // JSON request body for creating a new post
            String jsonBody = "[\r\n"
                               + "                  {\r\n"
                               + "                    \"sender\": \"RAKBANK\",\r\n"
                               + "                    \"destinations\": [\r\n"
                               + "                      {\r\n"
                               + "                        \"to\": \"971586660000\"\r\n"
                               + "                      }\r\n"
                               + "                    ],\r\n"
                               + "                    \"content\": {\r\n"
                               + "                      \"text\": \"This is a sample message\"\r\n"
                               + "                    }\r\n"
                               + "                  }\r\n"
                               + "                ]";

            Digital_PL.mLogger.debug("After JSON Request Construction" + jsonBody);

            try (DataOutputStream outputStream = new DataOutputStream(postConnection.getOutputStream())) {
                outputStream.writeBytes(jsonBody);
                outputStream.flush();
            }

            Digital_PL.mLogger.debug("After Output Stream flush");

            // Read POST response
            Digital_PL.mLogger.debug(postConnection.getResponseCode());
            int postResponseCode = postConnection.getResponseCode();
            StringBuilder postResponse = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(postConnection.getInputStream()))) {
                Digital_PL.mLogger.debug("Inside Response from Input Stream");
                String inputLine;
                while ((inputLine = reader.readLine()) != null) {
                    postResponse.append(inputLine);
                }
            }

            // Print POST response
            Digital_PL.mLogger.debug("POST Request Response:");
            Digital_PL.mLogger.debug("Status Code: " + postResponseCode);
            Digital_PL.mLogger.debug("Response Body: " + postResponse.toString());

            // Close connections
            postConnection.disconnect();
            return postResponseCode + "~" + postResponse.toString();

        } catch (Exception e) {
            Digital_PL.mLogger.debug("Error: " + e.getMessage());
            e.printStackTrace();
            return "Exception Occured in: " + e.getMessage();
        }
    }
	
	
	
	https://sms-interceptor-dev.rakbanktst.ae/sms-receiver/peopleevents/2/persons/2695757.2695757/definitions/BPMNBTLAP/events


{
                "properties": {
                  "WI_No": "NBTL-0000002148-Process"
                }
}

