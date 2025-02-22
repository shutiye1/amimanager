package safariami.manager.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
@IdClass(MeterDataId.class)
public class MeterData  {

    @Id
    @Column(name="meter_id")
    private long meterId;
    @Column(nullable = true)
    private String name;
    @Id
    private String obis;
    @Column(nullable = true)
    private String unit;
    @Column(precision=64, scale=3)
    private double value;

    @Column(nullable = true, name="capture_time")
    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:MM:ss'Z'")
    @Id
    private Date captureTime;

    public MeterData(long meterId, Date captureTime, String obis, String name, double value, String unit) {
        this(meterId, captureTime,obis,value, unit, false);
        this.name = name;
    }

    public MeterData() {

    }

    public MeterData(long meterId, Date captureTime,String obis, double value) {
        this(meterId, captureTime,obis,value, "", false);
    }

    public MeterData(long meterId, Date captureTime, String obis) {
        this(meterId, captureTime,obis,0, "", false);
    }

    public MeterData(long meterId, Date captureTime,String obis, double value, String unit, boolean isScale) {
        this.obis = obis;
        this.meterId = meterId;
        this.captureTime = captureTime;
        if (isScale) {
            value = value / 1000;
            setUnit((unit != null) ?  " k" + unit : " k");
        } else {
            setUnit((unit != null) ?  unit : " ");
        }
        setValue(value);
    }
    
    public MeterData(long meterId, Date captureTime,String obis, double value, boolean isScale) {
        this.obis = obis;
        this.meterId = meterId;
        this.captureTime = captureTime;
        if (isScale) {
            value = value / 1000;
            setUnit((unit != null) ?  " k" + unit : " k");
        } else {
            setUnit((unit != null) ?  unit : " ");
        }
        setValue(value);
    }

    public long getMeterId() {
        return meterId;
    }

    public void setMeterId(long meterId) {
        this.meterId = meterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObis() {
        return obis;
    }

    public void setObis(String obis) {
        this.obis = obis;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value =  value ;
    }

    public Date getCaptureTime() {
        return captureTime;
    }

    public void setCaptureTime(Date captureTime) {
        this.captureTime = captureTime;
    }

    @Override
    public String toString() {
        return "MeterData{" +
                "meterId=" + meterId +
                ", captureTime=" + captureTime +
                ", obis=" + obis +
                ", name='" + name  +
                ", value=" + value +
                ", unit=" + unit +
                '}'+"\n";
    }


}
