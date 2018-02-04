import java.util.ArrayList;
import java.util.List;

public class User {
	
	private String username;
	private String password; 
	private List<ChatMessage> awaitingMessages;
	
	public User(String name, String password) {
		setUsername(name);
		setPassword(password);
		awaitingMessages = new ArrayList<ChatMessage>();
	}
	
//	public void updateMessages(){
//		if (!this.awaitingMessages.isEmpty()) {
//			String query = "?";
//			for (String message : awaitingMessages){
//				
//			}
//		}
//	}
	public void recieveMessage(ChatMessage cm){
		getAwaitingMessages().add(cm);
	}
	
	public boolean hasAwaitingMessages(){
		return !this.awaitingMessages.isEmpty();
	}
	
	public List<ChatMessage> getAwaitingMessages(){
		return awaitingMessages;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
