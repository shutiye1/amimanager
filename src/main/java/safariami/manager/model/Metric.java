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
@Table(name = "metric")
public class Metric {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="created_at", insertable=false, updatable = false)
    private Date createdAt;
    
    @Column(name="updated_at", insertable=false, updatable = false)
    private Date updatedAt;
    
    @Column(name="started_at")
    private Date startedAt;
    
    @Column(name="action_counter")
    private long actionCounter; 
    
    @Column(name="event_counter")
    private long eventCounter;
    
    @Column(name="last_action_name")
    private String lastActionName;
    
    @Column(name="app_id")
    private long appId;
    
    @Column(name="dlms_port")
    private int dlmsPort;
    
    @Column(name="http_port")
    private int httpPort;
    
    @Column(name="ip")
    private String ip;
    
    public Metric() {
    	
    }
    
    public Metric(long appId, int dlmsPort, int httpPort, String ip) {
    	this.actionCounter = 0;
    	this.eventCounter  = 0;
    	this.lastActionName = "STARTUP";
    	this.appId = appId;
    	this.startedAt = new Date();
    	this.dlmsPort = dlmsPort;
    	this.httpPort = httpPort;
    	this.ip = ip;
    }

}
