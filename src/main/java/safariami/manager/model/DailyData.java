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
@Table(name = "daily_data")
public class DailyData {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long meterId;
    
    @Column(name="createdAt", insertable=false, updatable = false)
    private Date createdAt;
    
    private Date captureTime;
    private BigDecimal totalActiveImport;
    @Column(name="daily_increase")
    private BigDecimal totalActiveImportIncrease;
    private BigDecimal totalApparentExport;
    private BigDecimal activeIncrease;
    private BigDecimal commulativeTotalActive;
    private BigDecimal commulativeExportActive;
    private BigDecimal commulativeImportReactive;
    private BigDecimal commulativeExportReactive;
    private BigDecimal commulativeImportApparent;

    public DailyData() {
    	
    }
}
