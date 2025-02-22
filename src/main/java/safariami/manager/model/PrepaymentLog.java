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
@Table(name = "prepayment_log")
public class PrepaymentLog {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "{validation.NotBlank.message}")
	private String obis;
	private int counter;


	@NotBlank(message = "{validation.NotBlank.message}")
	@Column(name="serial_no")
	private String serialNo;

	@NotBlank(message = "{validation.NotBlank.message}")
	@Column(name="phone_number")
	private String phoneNumber;

	@Column(name="created_at", insertable=false, updatable = false)
	private Date createdAt;
	private int delay;
	private boolean retry;
	private BigDecimal totalActiveImport;


	public PrepaymentLog(){
		this.counter = 0;
		this.retry = false;
		this.delay = 0;
	}

	public PrepaymentLog(String serialNo, String phoneNumber, String obis, BigDecimal totalActiveImport) {
		this();
		this.serialNo = serialNo;
		this.obis = obis;
		this.phoneNumber = phoneNumber;
		this.totalActiveImport = totalActiveImport;
	}

	public PrepaymentLog(String serialNo, String phoneNumber, String obis, BigDecimal totalActiveImport, 
			int counter, int delay, boolean retry) {
		this.serialNo = serialNo;
		this.obis = obis;
		this.counter = counter;
		this.phoneNumber = phoneNumber;
		this.totalActiveImport = totalActiveImport;
		this.retry = retry;
		this.delay = delay;
	}

}
