package safariami.manager.model;

import java.util.Date;

import lombok.Data;

@Data
public class Notification {
    private int code;
    private Date timestamp;
    private String serial;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //private DataNotifications.Types type;

    public Notification() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

   /* public DataNotifications.Types getType() {
        return type;
    }

    public void setType(DataNotifications.Types type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("C:%d D:%s S:%s T:%s N:%s", getCode(), getTimestamp(), getSerial(), getType(), getName());
    }*/
}
