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
@Table(name = "demand_data")
public class DemandData {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long meterId;
    
    @Column(name="createdAt", insertable=false, updatable = false)
    private Date createdAt;
    
    private Date captureTime;
    private int programCount;
    private int outputState;
    private int powerDownCount;
    private int tamperCount;
    private int powerOnDuration;
    private int billingCount;
    private BigDecimal instActivePower;
    private BigDecimal totalImportActive;
    @Column(name="demand_increase")
    private BigDecimal totalImportActiveIncrease;
    private BigDecimal totalExportApparent;
    private double powerFactor;
    private double frequency;
    private BigDecimal exportActive;
    private BigDecimal instReactivePowerImport;
    private double currentPhaseOne;
    private double currentPhaseTwo;
    private double currentPhaseThree;
    private double voltagePhaseOne;
    private double voltagePhaseTwo;
    private double voltagePhaseThree;
    private BigDecimal instApparentPowerImport;
    private BigDecimal totalApparentImport;
    private double neutral;
    private double powerFactorPhaseOne;
    private double powerFactorPhaseTwo;
    private double powerFactorPhaseThree;
    private double commulativePowerDownTime;
    private double temperature;
    private double externalBatteryVoltage;
    private double unnamedOne;
    private double unnamedTwo;
    private double unnamedThree;
    private BigDecimal instActivePowerImportA;
    private BigDecimal instActivePowerExportA;
    private BigDecimal instReactivePowerImportA;
    private BigDecimal instReactivePowerExportA;
    private BigDecimal instApparentPowerImportA;
    private BigDecimal instApparentPowerExportA;
    private BigDecimal instActivePowerA;
    private BigDecimal instActivePowerImportB;
    private BigDecimal instActivePowerExportB;
    private BigDecimal instReactivePowerImportB;
    private BigDecimal instReactivePowerExportB;
    private BigDecimal instApparentPowerExportB;
    private BigDecimal instActivePowerB;
    private BigDecimal instActivePowerImportC;
    private BigDecimal instActivePowerExportC;
    private BigDecimal instReactivePowerImportC;
    private BigDecimal instReactivePowerExportC;
    private BigDecimal instApparentPowerImportC;
    private BigDecimal instApparentPowerExportC;
    private BigDecimal instApparentPowerExport;
    private BigDecimal instReactivePowerExport;
    private BigDecimal instActivePowerExport;
    private BigDecimal instReactivePower;
    private BigDecimal instActivePowerDup;
    private double angleBetweenVoltageA;
    private double angleBetweenVoltageB;
    private double angleBetweenVoltageC;

    
    public DemandData() {
    	
    }
}
