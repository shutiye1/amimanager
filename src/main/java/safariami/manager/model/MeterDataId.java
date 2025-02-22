package safariami.manager.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class MeterDataId  implements Serializable {
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	long meterId;
     String obis;
     Date captureTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeterDataId data = (MeterDataId) o;
        return (data.meterId) == meterId
                && data.obis.equals(obis)
                && data.captureTime != captureTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(meterId, captureTime, obis);
    }

}


