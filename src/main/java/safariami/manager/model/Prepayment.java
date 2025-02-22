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
@Table(name = "prepayment")
public class Prepayment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    private String customerId;
    private BigDecimal initialReading;
    private BigDecimal overdraftThreshold;
    private int maxNotification;
    private double ratePlan;
    private String customerPhone;
    private String serialNo;
    private String meterPhone;
    private boolean isMeterAMI;
    
    @Column(name="createdAt", insertable=false, updatable = false)
    private Date createdAt;
    
    @Column(name="updatedAt", insertable=false, updatable = false)
    private Date updatedAt;
    
    public Prepayment() {
    	
    }

    
    public Prepayment(String customerId, BigDecimal initialReading, BigDecimal overdraftThreshold, 
    		int maxNotification, double ratePlan, String serialNo, String customerPhone, String meterPhone, boolean isMeterAMI) {
    	this.customerId = customerId;
    	this.initialReading = initialReading;
    	this.overdraftThreshold = overdraftThreshold;
    	this.maxNotification = maxNotification;
    	this.ratePlan = ratePlan;
    	this.serialNo = serialNo;
    	this.customerPhone = customerPhone;
    	this.meterPhone = meterPhone;
    	this.isMeterAMI = isMeterAMI;
    }
}
