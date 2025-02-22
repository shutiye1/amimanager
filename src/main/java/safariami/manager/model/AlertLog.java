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
@Table(name = "alert_log")
@IdClass(AlertId.class)
public class AlertLog {
    @Id
    @Column(name="serial_no")
    private String  serialNo;
    @Id
    @Column(name="alert_code")
    private int alertCode;
    @Id
    @Column(name="capture_time")
    private Date captureTime;

    public AlertLog() {
    }
    public AlertLog(String serialNo, int alertCode) {
        this.serialNo = serialNo;
        this.alertCode = alertCode;
    }


    public AlertLog(String serialNo, int alertCode, Date captureTime) {
        this.serialNo = serialNo;
        this.alertCode = alertCode;
        this.captureTime = captureTime;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public int getAlertCode() {
        return alertCode;
    }

    public void setAlertCode(int alertCode) {
        this.alertCode = alertCode;
    }

    public Date getCaptureTime() {
        return captureTime;
    }

    public void setCaptureTime(Date captureTime) {
        this.captureTime = captureTime;
    }


    @Override
    public String toString() {
        return "AlertLog{" +
                "serialNo='" + serialNo + '\'' +
                ", alertCode=" + alertCode +
                ", captureTime=" + captureTime +
                '}';
    }
}
