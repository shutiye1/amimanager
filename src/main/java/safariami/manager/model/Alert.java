package safariami.manager.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "alert")
@IdClass(AlertId.class)
public class Alert {

    @Id
    @Column(name="serial_no")
    private String serialNo;


    @Column(name="alert_name")
    private String alertName;

    @Column(name="customer_seq_no")
    private String customerSeqNo;

    @Id
    @Column(name="alert_code")
    private int alertCode;

    @Id
    @Column(name="capture_time")
    private Date captureTime;

    public Alert() {
    }

    public Alert(String serialNo) {
        this.serialNo = serialNo;
    }

    public Alert(String serialNo, Date captureTime) {
        this.serialNo = serialNo;
        this.captureTime = captureTime;
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

    public Date getCaptureTime() {
        return captureTime;
    }

    public void setCaptureTime(Date captureTime) {
        this.captureTime = captureTime;
    }

    public String getAlertName() {
        return alertName;
    }

    public void setAlertName(String alertName) {
        this.alertName = alertName;
    }

    public int getAlertCode() {
        return alertCode;
    }

    public void setAlertCode(int alertCode) {
        this.alertCode = alertCode;
    }


}
