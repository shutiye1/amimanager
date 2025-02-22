package safariami.manager.model;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "monthly_data")
public class Monthly extends MeterData {


    public Monthly(){

    }

    public Monthly(long meterId, Date captureTime, String obis, String name, double value, String unit) {
        super(meterId, captureTime, obis, name, value, unit);
    }


}
