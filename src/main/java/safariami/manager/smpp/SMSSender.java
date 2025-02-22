package safariami.manager.smpp;

import java.io.PrintWriter;
import java.io.StringWriter;
import com.fasterxml.jackson.databind.ObjectMapper;

import safariami.manager.util.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class SMSSender {

    private String serverUrl;

    private static Logger log = LoggerFactory.getLogger(SMSSender.class);

    public SMSSender() {
    	this.serverUrl = "http://"+Constants.SMS_SERVER_IP +":9090/sendSMS";
    }


    /**
     * Send SMS to the server.
     * Defaults: sender=192, message=CMD=0;, serverUrl:http://localhost:9090/sendSMS
     *
     * @param phone - SMS recipient
     * @return 200=Success, 500=SDPNODE Failed to send SMS, 501=SDPNODE Timed out,
     * 502=Empty phone number, 503=Empty response from server
     */
    public int sendSMS(String phone) {

        if (phone == null || phone.length() <= 0) return 502;
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Build request
            String jsonRequest = "";
            if (this.serverUrl.contains("send?no=")) {
                HttpEntity<String> request = new HttpEntity<String>(jsonRequest);
                RestTemplate restTemplate = customRestTemplate();
                ResponseEntity<String> response = restTemplate.exchange(serverUrl + phone,
                        HttpMethod.GET, request, String.class);
                if (response == null || response.getBody() == null || response.getBody().length() <= 0) return 503;

                // Build response
                GoaadSMSResponse smsResponse = mapper.readValue(response.getBody(), GoaadSMSResponse.class);

                if (smsResponse == null) {
                    return 503;
                } else if (smsResponse.statusCode.equals("success")) {
                    return 200;
                } else {
                    return 404;
                }
            } else {

                SMSRequest smsRequest = new SMSRequest();
                smsRequest.DestAddr = phone;
                jsonRequest = mapper.writeValueAsString(smsRequest);
                RestTemplate restTemplate = customRestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> request = new HttpEntity<String>(jsonRequest,headers);
                ResponseEntity<String> response = restTemplate.exchange(serverUrl,
                        HttpMethod.POST, request, String.class);

                if (response == null || response.getBody() == null || response.getBody().length() <= 0) return 503;

                // Build response
                SMSResponse smsResponse = mapper.readValue(response.getBody(), SMSResponse.class);

                if (smsResponse == null) return 503;

                return smsResponse.statusCode;
            }


        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error("Exception StackTrace:" + sw.toString());
        }

        return 503;
    }
    
    /**
     * Send SMS to the server.
     * Defaults: sender=192, message=CMD=0;, serverUrl:http://localhost:9090/sendSMS
     *
     * @param phone - SMS recipient
     * @return 200=Success, 500=SDPNODE Failed to send SMS, 501=SDPNODE Timed out,
     * 502=Empty phone number, 503=Empty response from server
     */
    public int sendSMS(String phone, String message, String srcAddr) {

        if (phone == null || phone.length() <= 0) return 502;
        ObjectMapper mapper = new ObjectMapper();
        try {
        	// Build request

        	SMSRequest smsRequest = new SMSRequest();
        	smsRequest.DestAddr = phone;
        	smsRequest.SrcAddr = srcAddr;
        	smsRequest.ShortMessage = message;
        	String jsonRequest = mapper.writeValueAsString(smsRequest);
        	RestTemplate restTemplate = customRestTemplate();
        	HttpHeaders headers = new HttpHeaders();
        	headers.setContentType(MediaType.APPLICATION_JSON);
        	HttpEntity<String> request = new HttpEntity<String>(jsonRequest,headers);
        	ResponseEntity<String> response = restTemplate.exchange(serverUrl,
        			HttpMethod.POST, request, String.class);

        	if (response == null || response.getBody() == null || response.getBody().length() <= 0) return 503;

        	// Build response
        	SMSResponse smsResponse = mapper.readValue(response.getBody(), SMSResponse.class);

        	if (smsResponse == null) return 503;

        	return smsResponse.statusCode;


        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error("Exception StackTrace:" + sw.toString());
        }

        return 503;
    }

    @Bean
    public RestTemplate customRestTemplate() {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        //httpRequestFactory.setConnectionRequestTimeout();
        httpRequestFactory.setConnectTimeout(5*1000); //5s
        //httpRequestFactory.setReadTimeout(10*1000); //10s

        return new RestTemplate(httpRequestFactory);
    }
}
