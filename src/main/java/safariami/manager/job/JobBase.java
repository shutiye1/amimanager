package safariami.manager.job;

import org.apache.commons.lang3.SystemUtils;


public  class JobBase {

    protected JobBase() {
    }

   public static String getHesId() throws Exception {
       String hesId = null;
       if(SystemUtils.IS_OS_LINUX){
           hesId = System.getProperty("HES_ID");
       }else {
           hesId = System.getenv("HES_ID");
       }
       if(hesId == null || hesId.length() <= 0) {
           throw new Exception("HES ID not defined");
       }else {
           return hesId;
       }
    }

}
