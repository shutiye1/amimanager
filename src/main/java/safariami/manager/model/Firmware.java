package safariami.manager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "firmware")
public class Firmware {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	private String name;
	private String type;
	private String version;
	private byte[] image;
    @Column(name="job_id")
    private long jobId;
    @Column(name="image_id")
    private String imageId;
	
	public Firmware() {
		
	}

	public Firmware(String name, String type, String version, byte[] image) {
		this.name = name;
		this.type = type;
		this.version = version;
		this.image = image;
	}
	
	public Firmware(String name, String type, String version, byte[] image, long jobId, String imageId) {
		this.name = name;
		this.type = type;
		this.version = version;
		this.image = image;
		this.jobId = jobId;
		this.imageId = imageId;
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	
	
}
