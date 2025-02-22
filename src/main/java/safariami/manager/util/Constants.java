package safariami.manager.util;

public class Constants {

	public static String SMS_SERVER_IP;
	public static int MAX_NOTIFICATION = 2;
	public static String TOPUP_SHORCODE;
	public static String UTILITY_NAME;
	public static String UTILITY_ACCOUNT;
	
	public static enum OverdraftStatus {
		DISCONNECTED,
		CONNECTED
	}
	
	/*public static enum AccountPrefix {
		E,
		I
	}
	
	public static enum AccountType {
		EXTERNRAL,
		INTENRAL
	}*/
	
	public static enum TransactionType {
		CREDIT,
		DEBIT
	}
	
	public static enum ActionName {
		DEMAND,
		MONTHLY,
		DAILY,
		HOURLY,
		DATA_CONFIG,
		DISCONNECT,
		CONNECT,
		APN,
		TAMPER,
		STATE_CONFIG,
		METER_FW_UPDATE,
		MODULE_FW_UPDATE,
		GENERIC_ACTION,
		TOKEN_GEN,
		METER_FW_CHECK,
		MODULE_FW_CHECK,
		FTP_SETUP,
		SCHEDULED_WAKE_UP_SETUP
	}
	
	public static enum ActionStatus {
		NEW,
		FAILED,
		IN_PROGRESS,
		COMPLETED,
		METER_OFFLINE,
		AUTO_RETRY
	}
	
	// Higher number means highest
	public static enum JobPriorty {
		MONTHLY(100),
		DEMAND(95),
		DAILY(90),
		HOURLY(50),
		GENERIC_ACTION(20),
		FIRMWARE_UPDATE(10);
		
		private final int value;
		
	    private JobPriorty(int value) {
	        this.value = value;
	    }
	    public int getValue() {
	        return value;
	    }
	}
	
	public static enum JobStatus {
		RUNNING,
		READY_TO_RUN,
		COMPLETED,
		HALTED
	}
	
	public static enum JobNames {
		FIRMWARE,
		HOURLY,
		DAILY,
		MONTHLY,
		DEMAND,
		GENERIC_JOB,
		METER_FW_CHECK,
		MODULE_FW_CHECK,
		FTP_SETUP,
		SCHEDULED_WAKE_UP_SETUP
	}

}
