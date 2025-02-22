package safariami.manager.model;


import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class AlertId  implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int alertCode;
    Date captureTime;
    String serialNo;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlertId data = (AlertId) o;
        return  data.serialNo.equals(serialNo)
                && data.captureTime == captureTime
                && data.alertCode == alertCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNo, captureTime, alertCode);
    }

}
