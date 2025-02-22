package safariami.manager.model;

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
@Table(name = "customer")
public class Customer {
	
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;
    private String customerId;
    private String password;
    private String address;
    
    @Column(name="createdAt", insertable=false, updatable = false)
    private Date createdAt;
    
    @Column(name="updatedAt", insertable=false, updatable = false)
    private Date updatedAt;
    
    public Customer() {
    	
    }

    public Customer(String firstName, String middleName, String lastName, String email, String phone,
    		String customerId, String password, String address) {
    	this.firstName = firstName;
    	this.middleName = middleName;
    	this.lastName = lastName;
    	this.email = email;
    	this.phone = phone;
    	this.customerId = customerId;
    	this.password = password;
    	this.address = address;
    }

}
