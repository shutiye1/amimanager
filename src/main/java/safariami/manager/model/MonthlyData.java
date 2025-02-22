package safariami.manager.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "monthly_data_new")
public class MonthlyData {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long meterId;
    
    @Column(name="createdAt", insertable=false, updatable = false)
    private Date createdAt;
    
    private Date captureTime;
    private double billingPeriodAveragePowerFactor;
    private BigDecimal totalActive;
    private BigDecimal totalImportActive;
    private BigDecimal exportActive;
    private BigDecimal importReactive;
    private BigDecimal exportReactive;
    private BigDecimal totalImportApparent;
    private BigDecimal totalExportApparent;
    private BigDecimal monthActiveIncrease;

    public MonthlyData() {
    	
    }
}
