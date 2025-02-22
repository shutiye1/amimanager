package safariami.manager.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "job")
public class Job {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String name;
	private String type;
	
	@NotNull
	private String status;
	
	@Column(name="created_at", insertable=false, updatable = false)
	private Date createdAt;
	@Column(name="updated_at", insertable=false, updatable = false)
	private Date updatedAt;
	private int priority;
	@Column(name="start_at")
	private Date startAt;
	@Column(name="end_at")
	private Date endAt;
	
	private String obis;
	@Column(name="ct_start_at")
	private Date ctStartdAt;
	
	private String extra;
	
	public Job() {
		
	}
	
	public Job(String name, String type, String status) {
		this.name = name;
		this.type = type;
		this.status = status;
	}
	
	public Job(String name, String type, String status, String extra) {
		this.name = name;
		this.type = type;
		this.status = status;
		this.extra = extra;
	}
	
	public Job(String name, String type, String status, int priority) {
		this.name = name;
		this.type = type;
		this.status = status;
		this.priority = priority;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Date getStartAt() {
		return startAt;
	}

	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}

	public Date getEndAt() {
		return endAt;
	}

	public void setEndAt(Date endAt) {
		this.endAt = endAt;
	}

	public String getObis() {
		return obis;
	}

	public void setObis(String obis) {
		this.obis = obis;
	}

}
