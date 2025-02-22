package safariami.manager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "alert_type")
public class AlertType {
    @Column(name="alert_name")
    private String alertName;

    @Id
    @Column(name="alert_code")
    private long alertCode;


    @Column(name="alert_type")
    private boolean alertType;


    @Column(name="is_critical")
    private boolean isCritical;


    @Column(name="alert_obis")
    private String alertObis;


    public AlertType() {
    }

    public AlertType(String alertName, long alertCode, boolean alertType, String alertObis) {
        this.alertName = alertName;
        this.alertCode = alertCode;
        this.alertType = alertType;
        this.alertObis = alertObis;
    }


    public String getAlertName() {
        return alertName;
    }

    public void setAlertName(String alertName) {
        this.alertName = alertName;
    }

    public long getAlertCode() {
        return alertCode;
    }

    public void setAlertCode(long alertCode) {
        this.alertCode = alertCode;
    }

    public boolean getAlertType() {
        return alertType;
    }

    public void setAlertType(boolean alertType) {
        this.alertType = alertType;
    }

    public String getAlertObis() {
        return alertObis;
    }

    public void setAlertObis(String alertObis) {
        this.alertObis = alertObis;
    }

    public boolean isCritical() {
        return isCritical;
    }

    public void setCritical(boolean critical) {
        isCritical = critical;
    }

    @Override
    public String toString() {
        return  " name: "+ alertName +
                " code: "+ alertCode +
                " type: "+ alertType +
                " obis: "+ alertObis + "\n";
    }
}
