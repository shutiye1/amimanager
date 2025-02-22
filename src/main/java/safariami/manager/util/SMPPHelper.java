package safariami.manager.util;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import safariami.manager.smpp.SMSSender;

/**
 * Created by AhmedOzmaan on 6/24/2018.
 */
public class SMPPHelper {

    private static Logger log = LoggerFactory.getLogger(SMPPHelper.class);
    private String action;
    private String serial;
    private String phone;


    public SMPPHelper(String action, String serial, String phone) {
        this.action = action;
        this.serial = serial;
        this.phone = phone;
    }

    public boolean sendSMSWithRetry() {
    	
        SMSSender smsSender = new SMSSender();
        int retryCount = 0;
        boolean sent = false;

        while (retryCount < 5) {

            int status = smsSender.sendSMS(this.phone);

            // Successfully sent message has status = 200
            if (status == 200) {
                sent = true;
                break;
            }

            retryCount++;
        }
        if (sent) {
            log.info(this.action + ": SMS sent to: " + this.serial + ": on: " + this.phone);
        } else {
            log.info(this.action + ": Unable to send SMS to: " + this.serial + ": on: " + this.phone);
        }

        return sent;
    }
    
    public boolean sendSMSWithRetry(String message, String srcAddr) {
    	
        SMSSender smsSender = new SMSSender();
        int retryCount = 0;
        boolean sent = false;

        while (retryCount < 5) {

            int status = smsSender.sendSMS(this.phone, message, srcAddr);

            // Successfully sent message has status = 200
            if (status == 200) {
                sent = true;
                break;
            }

            retryCount++;
        }
        if (sent) {
            log.info(this.action + ": SMS sent to: " + this.serial + ": on: " + this.phone);
        } else {
            log.info(this.action + ": Unable to send SMS to: " + this.serial + ": on: " + this.phone);
        }

        return sent;
    }
    
    public boolean sendSMSWithRetryMock() {
    	if(Strings.isNotEmpty(action) && Strings.isNotEmpty(phone) && Strings.isNotEmpty(serial)) {
    		return true;
    	}
    	
    	return false;
    }

}
