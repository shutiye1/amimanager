package safariami.manager.model;


import java.io.Serializable;
import java.util.Date;

public class BillId implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	long meterId;
    String serialNo;
    Date billDate;
    String billMonth;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillId data = (BillId) o;
        return (data.meterId) == meterId
                && data.serialNo.equals(serialNo)
                && data.billMonth.equals(billMonth)
                && data.billDate != billDate;
    }
}