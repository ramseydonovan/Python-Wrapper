
public class ChatMessage {
	
	private String username ="";
	private String message = "";
	
	public ChatMessage(String username, String message){
		this.setUsername(username); 
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
