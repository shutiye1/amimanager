package safariami.manager.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "demand_data")
public class Demand extends MeterData {

    public Demand() {}

    public Demand(long meterId, Date captureTime, String obis, String name, double value, String unit) {
        super(meterId, captureTime, obis, name, value, unit);
    }



}
