import java.util.ArrayList;
import java.util.List;

public class ChatMessageManager {
	
	/**
	 * Holds all sent chat messages
	 */
	private List<ChatMessage> chatMessages;
	
	public ChatMessageManager(){
		chatMessages = new ArrayList<ChatMessage>(); 
	}
	
	public void addMessageToChat(ChatMessage message){
		chatMessages.add(message);
	}
	
	public List<ChatMessage> getChatMessages(){
		return chatMessages; 
	}
	
}
