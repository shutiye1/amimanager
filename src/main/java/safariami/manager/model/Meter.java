package safariami.manager.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "meter")
public class Meter {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "{validation.username.NotBlank.message}")
    @Column(name="serial_no")
    private String serialNo;

    @NotBlank(message = "{validation.username.NotBlank.message}")
    @Column(name="customer_seq_no")
    private String customerSeqNo;


    @NotBlank(message = "{validation.username.NotBlank.message}")
    @Column(name="phone_number")
    private String phoneNumber;

    private boolean online;
    @Column(name="online_at")
    private Date onlineAt;
    private boolean power;
    private boolean CT;
    @Column(name="ak_ek")
    private String akEk;
    @Column(name="module_fw")
    private String moduleFw;
    @Column(name="meter_fw")
    private String meterFw;
    @Column(name="is_active")
    private boolean isActive;

    @Column(name="ct_ratio")
    private double ctRatio;
    @Column(name="vt_ratio")
    private double vtRatio;
    @Column(name="is_prepayment")
    private boolean isPrepayment;
    @Column(name="is_ami")
    private boolean isAMI;
    
    @Column(name="ftp_setup")
    private boolean isFTPSetup;
    @Column(name="meter_fw_setup")
    private boolean isMeterFwSetup;
    @Column(name="module_fw_setup")
    private boolean isModuleFwSetup;
    @Column(name="schedule_setup")
    private boolean isScheduleSetup;

    public Meter() {
    }

    public Meter(String serialNo,int phase) {
        this.serialNo = serialNo;
    }

    public String getAkEk() {
        return akEk;
    }

    public void setAkEk(String akEk) {
        this.akEk = akEk;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public boolean isCT() {
        return CT;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
    
    public boolean getOnline() {
        return this.online;
    }

    public boolean isPower() {
        return power;
    }

    public void setPower(boolean power) {
        this.power = power;
    }

    public void setCT(boolean CT) {
        this.CT = CT;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


    public String getMeterFw() {
        return meterFw;
    }

    public void setMeterFw(String meterFw) {
        this.meterFw = meterFw;
    }

    public String getModuleFw() {
        return moduleFw;
    }

    public void setModuleFw(String moduleFw) {
        this.moduleFw = moduleFw;
    }

    public double getCtRatio() {
        return ctRatio;
    }

    public void setCtRatio(double ctRatio) {
        this.ctRatio = ctRatio;
    }

    public double getVtRatio() {
        return vtRatio;
    }

    public void setVtRatio(double vtRatio) {
        this.vtRatio = vtRatio;
    }
}
