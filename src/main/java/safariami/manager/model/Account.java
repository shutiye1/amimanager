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
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private BigDecimal balance;
    private String accountId;
    private String token;
    
    @Column(name="createdAt", insertable=false, updatable = false)
    private Date createdAt;
    
    @Column(name="updatedAt", insertable=false, updatable = false)
    private Date updatedAt;
    
    public Account() {
    	
    }
    
    public Account(BigDecimal balance) {
    	this.balance = balance;
    }

    public Account(BigDecimal balance, String accountId) {
    	this.balance = balance;
    	this.accountId = accountId;
    }
}
