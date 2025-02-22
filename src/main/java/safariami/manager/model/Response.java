package safariami.manager.model;

public class Response<T>  {
    private String state;
    private String message;

    private T body;


    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public Response(String state, String message) {
        this.state = state;
        this.message = message;
    }
    public Response(String state, String message, T body) {
        this.state = state;
        this.message = message;
        this.body = body;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
