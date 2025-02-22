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
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private String accountId;
    private String type;
    private String token;
    
    @Column(name="createdAt", insertable=false, updatable = false)
    private Date createdAt;
    
    @Column(name="updatedAt", insertable=false, updatable = false)
    private Date updatedAt;
    
    public Transaction() {
    	
    }

    public Transaction(BigDecimal amount, String accountId, String type, String token) {
    	this.amount = amount;
    	this.accountId = accountId;
    	this.type = type;
    	this.token = token;
    }
}
