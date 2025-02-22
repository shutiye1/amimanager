package safariami.manager.util.error;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CustomResponse {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private String data;
    
    public CustomResponse() {
    	
    }
    
    public CustomResponse(String message, int status) {
    	this.message = message;
    	this.status  = status;
    }
    
    public CustomResponse(String data, String message, int status) {
    	this.data = data;
    	this.message = message;
    	this.status  = status;
    }


}
