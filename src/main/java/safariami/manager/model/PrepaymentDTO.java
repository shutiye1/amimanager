package safariami.manager.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PrepaymentDTO {
	
	@NotNull
	@JsonProperty("account_number")
	private String customerId;
	@NotNull
	private String phone;
	@JsonProperty("first_name")
	private String firstName;
	@JsonProperty("middle_name")
	private String middleName;
	@JsonProperty("last_name")
	private String lastName;
	private String email;
	private String address;
	private String password;
	
	//@NotNull
	@JsonProperty("initial_reading")
    private BigDecimal intialReading;
	//@NotNull
	@JsonProperty("overdraft_threshold")
    private BigDecimal overdraftThreshold;

	@JsonProperty("max_notification")
    private Integer maxNotification;
	@NotNull
	@JsonProperty("rate_plan")
    private Double ratePlan;
	
	public PrepaymentDTO() {
		
	}

}
