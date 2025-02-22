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
@Table(name = "prepayment_overdraft")
public class PrepaymentOverdraft {
	
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private BigDecimal overdraftAmount;
    private String customerId;
    private String status;
    private int warningCount;
	
    @Column(name="createdAt", insertable=false, updatable = false)
    private Date createdAt;
    
    @Column(name="updatedAt", insertable=false, updatable = false)
    private Date updatedAt;

    public PrepaymentOverdraft() {
    	
    }
    
    public PrepaymentOverdraft(String customerId, BigDecimal overdraftAmount, String status, int warningCount) {
    	this.customerId = customerId;
    	this.overdraftAmount = overdraftAmount;
    	this.status = status;
    	this.warningCount = warningCount;
    }
}
