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
@Table(name = "hourly_data")
public class HourlyData {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long meterId;
    
    @Column(name="createdAt", insertable=false, updatable = false)
    private Date createdAt;
    
    private Date captureTime;
    private BigDecimal profileStatus;
    private BigDecimal blockImportActive;
    private BigDecimal blockExportActive;
    private BigDecimal blockImportReactive;
    private BigDecimal blockExportReactive;
    private BigDecimal blockImportApparent;
    private BigDecimal blockExportApparent;
    private BigDecimal cumulativeImportActiveEnergy;
    @Column(name="hourly_increase")
    private BigDecimal cumulativeImportActiveEnergyIncrease;
    private BigDecimal cumulativeExportActiveEnergy;
    private BigDecimal cumulativeImportApparentEnergy;
    private BigDecimal cumulativeExportApparentEnergy;
    private double currentPhaseOne;
    private double currentPhaseTwo;
    private double currentPhaseThree;
    private double voltagePhaseOne;
    private double voltagePhaseTwo;
    private double voltagePhaseThree;
    private double powerFactorPhaseOne;
    private double powerFactorPhaseTwo;
    private double powerFactorPhaseThree;
    private double neutralInstantCurrent;
    private double totalInstantPowerFactor;
    private double frequency;

    
    public HourlyData() {
    	
    }
}
