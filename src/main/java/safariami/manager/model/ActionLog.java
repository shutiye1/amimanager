package safariami.manager.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import safariami.manager.util.Constants;

@Data
@Entity
@Table(name = "action_log")
public class ActionLog {
	
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "{validation.NotBlank.message}")
    private String obis;
    private String type;
    private Date end;
    private int counter;
    private String status;
    private Date start;
    
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
    @Column(name="job_id")
    private long jobId;
    
    private String extra;


    public ActionLog(){

    }

    public ActionLog(String serialNo, String phoneNumber, String obis, String type, Date end, Date start) {
    	this.serialNo = serialNo;
        this.obis = obis;
        this.type = type;
        this.end = end;
        this.start = start;
        counter = 0;
        status = Constants.ActionStatus.NEW.name();
        this.phoneNumber = phoneNumber;

    }
    
    public ActionLog(String serialNo, String phoneNumber, String obis, String type, Date end, Date start, long jobId) {
    	this.serialNo = serialNo;
        this.obis = obis;
        this.type = type;
        this.end = end;
        this.start = start;
        counter = 0;
        status = Constants.ActionStatus.NEW.name();
        this.jobId = jobId;
        this.phoneNumber = phoneNumber;

    }
    
    public ActionLog(String serialNo, String phoneNumber, String obis, String type, Date end, Date start, long jobId, String extra) {
    	this.serialNo = serialNo;
        this.obis = obis;
        this.type = type;
        this.end = end;
        this.start = start;
        counter = 0;
        status = Constants.ActionStatus.NEW.name();
        this.jobId = jobId;
        this.phoneNumber = phoneNumber;
        this.extra = extra;

    }
    
    public void setCounter(int counter) {
        this.counter += counter;
    }
    
    public void resetCounter() {
        this.counter = 0;
    }

}
