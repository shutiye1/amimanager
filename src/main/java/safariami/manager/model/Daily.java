package safariami.manager.model;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "daily_data")
public class Daily extends MeterData   {

    public Daily(){

    }
    public Daily(long meterId, Date captureTime, String obis, String name, double value, String unit) {
        super(meterId, captureTime, obis, name, value, unit);
    }


}
