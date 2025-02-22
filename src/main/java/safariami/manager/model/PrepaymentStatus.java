package safariami.manager.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "prepayment_status")
public class PrepaymentStatus {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "{validation.NotBlank.message}")
	@Column(name="serial_no")
	private String serialNo;

	@NotBlank(message = "{validation.NotBlank.message}")
	@Column(name="phone_number")
	private String phoneNumber;

	@Column(name="created_at", insertable=false, updatable = false)
	private Date createdAt;
	@Column(name="updated_at", insertable=false, updatable = false)
	private Date updatedAt;
	@Column(name="prev_reading")
	private BigDecimal prevReading;
	private String status;
	@Column(name="current_reading")
	private BigDecimal currentReading;

	public PrepaymentStatus(){

	}


	public PrepaymentStatus(String serialNo, String phoneNumber, BigDecimal prevReading, String status, BigDecimal currentReading) {
		this.serialNo = serialNo;
		this.phoneNumber = phoneNumber;
		this.prevReading = prevReading;
		this.status = status;
		this.currentReading = currentReading;
	}

}
