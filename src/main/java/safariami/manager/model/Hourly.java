package safariami.manager.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "hourly_data")
public class Hourly extends MeterData  {

    public Hourly(){

    }
    public Hourly(long meterId, Date captureTime, String obis, String name, double value, String unit) {
        super(meterId, captureTime, obis, name, value, unit);
    }

}
