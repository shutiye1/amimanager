package safariami.manager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "bill")
public class Bill {

    public Bill(){

    }
    public Bill(long meterId, String serialNo, String customerSeqNo, Date billDate) {
        this.meterId = meterId;
        this.serialNo = serialNo;
        this.customerSeqNo = customerSeqNo;
        this.billDate = billDate;
        this.currentRead ="";
        this.lastRead = "";
        this.meterUsage = "";
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="meter_id")
    private long meterId;

    @Column(name="serial_no")
    private String serialNo;

    @Column(name="bill_month")
    private String billMonth;

    @Column(name="seq_num")
    private String customerSeqNo;

    @Column(name="current_read")
    private String currentRead;

    @Column(name="last_read")
    private String lastRead;

    @Column(name="meter_usage")
    private String meterUsage;

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

    public String getMeterUsage() {
        return meterUsage;
    }

    public void setMeterUsage(String meterUsage) {
        this.meterUsage = meterUsage;
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

    @Override
    public String toString() {
        return "Bill{" +
                "meterId=" + meterId +
                ", serialNo='" + serialNo + '\'' +
                ", customerSeqNo='" + customerSeqNo + '\'' +
                ", currentRead='" + currentRead + '\'' +
                ", lastRead='" + lastRead + '\'' +
                ", meterUsage='" + meterUsage + '\'' +
                ", billDate=" + billDate +
                '}';
    }
}
