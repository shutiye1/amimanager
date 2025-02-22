package safariami.manager.model;

import org.hibernate.annotations.Immutable;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.util.Date;

@Data
@Entity
@Immutable
@Table(name = "bill_view")
@IdClass(BillId.class)
public class BillView {

    @Id
    @Column(name="meter_id")
    private long meterId;

    @Id
    @Column(name="serial_no")
    private String serialNo;

    @Id
    @Column(name="seq_num")
    private String customerSeqNo;


    @Id
    @Column(name="bill_month")
    private String billMonth;

    @Column(name="current_read")
    private String currentRead;

    @Column(name="last_read")
    private String lastRead;

    @Column(name="meter_usage")
    private String meter_usage;



    @Column(name="bill_date")
    Date billDate;

    public long getMeterId() {
        return meterId;
    }

    public void setMeterId(long meterId) {
        this.meterId = meterId;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getCustomerSeqNo() {
        return customerSeqNo;
    }

    public void setCustomerSeqNo(String customerSeqNo) {
        this.customerSeqNo = customerSeqNo;
    }

    public String getCurrentRead() {
        return currentRead;
    }

    public void setCurrentRead(String currentRead) {
        this.currentRead = currentRead;
    }

    public String getLastRead() {
        return lastRead;
    }

    public void setLastRead(String lastRead) {
        this.lastRead = lastRead;
    }

    public String getMeter_usage() {
        return meter_usage;
    }

    public void setMeter_usage(String meter_usage) {
        this.meter_usage = meter_usage;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getBillMonth() {
        return billMonth;
    }

    public void setBillMonth(String billMonth) {
        this.billMonth = billMonth;
    }
}
