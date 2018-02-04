import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ChatServlet extends HttpServlet {
	
   ChatMessageManager chatMessageManager = new ChatMessageManager();
   Map<String, User> userMap = new HashMap();
   private static List<User> users = new ArrayList<User>();

   public void init() throws ServletException {
      // Do required initialization
//      message = "message";
//      username = "Default";
      
   }
   
   // When new message manager needed
   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
	   System.out.println("inside doGet");
	   HttpSession session = request.getSession();
	   
//	   String username = request.getParameter("username");
//	   String message = request.getParameter("message");
	   
//	   User currentUser = new User(username, message);
	   
//	   users.add(currentUser);
//	   session.setAttribute("user", currentUser);
	   
	   // each person needs their own chat 
	   // have a map of usernames, if that user update for his screen for his perspective, else update everyones for their perspective

//	   response.sendRedirect("ChillHub/html/ChillHub/chat_room.html"); //message=" + message + "&username=" + username);
   }

   public synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   System.out.println("in doPost");
	   HttpSession session = request.getSession();
	   
	   String requestType = request.getParameter("type");
	   String username = request.getParameter("username");  
 
//	   if (session.isNew()) {
//		   // is it a login or is it a new user. 
//		   doGet(request, response);
//	   }
//	   else {
	   PrintWriter pw = null;
	   Document doc = null;
	   TransformerFactory tFactory = TransformerFactory.newInstance();
	   Transformer transformer = null;
	     
	     try {
	       transformer = tFactory.newTransformer();
	     } catch (Exception e) {
	       e.printStackTrace();
	     }
	     
	     pw = response.getWriter();
	     boolean noResponse = false;
	     
	   switch (requestType) {
		   case "newUser":{
			   if (userMap.containsKey(username)){
				   System.out.println("used userName " + username);
				   //username already exists sorry
				   response.sendRedirect("ChillHub/html/ChillHub/username_taken.html");
			   } 
			   else {
				  // username is free, you got it
				   System.out.println("new userName " + username);
				   String password = request.getParameter("password");
				   User newUser = new User(username, password);
				   userMap.put("username",newUser);
				   session.setAttribute("user", newUser);
				   // need to store the user in a data base as well 
				   response.sendRedirect("ChillHub/html/ChillHub/chat_room.html");
			   }
			   break;
		   }
		   case "poll": {
			   //get user, check if they have any messages from other people
			   User user = (User)session.getAttribute("user");
			   if (user.hasAwaitingMessages()){
				 doc = awaitingMessagesAsXMLDoc(user);
			   }
			   noResponse = true;
			   break;
		   }
		   case "message":{
			   String message = request.getParameter("message");
			     ChatMessage chatMessage = new ChatMessage(username, message);
			  	 System.out.println(message);
			     sendMessagesToOtherUses(username, chatMessage );
			     doc = messageAsXMLDoc(username, message);
			     break;
		   }
	   }
	     
	     if (!noResponse) {
	     try {
	          transformer.transform(new DOMSource(doc), new StreamResult(pw));
	        } catch (Exception e) {
	          e.printStackTrace();
	        }
	     }
	   }
//   }
   
   
   public Document messageAsXMLDoc(String username, String message) {
       DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
       DocumentBuilder dBuilder = null;
		 try {
			  dBuilder = dbFactory.newDocumentBuilder();
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
       Document doc = dBuilder.newDocument();

       Element root =doc.createElement("chat");
       doc.appendChild(root);
       
       Element chatMessage = doc.createElement("chat-message");
       chatMessage.setAttribute("username", username);
       chatMessage.setAttribute("message", message);
       
       root.appendChild(chatMessage);
   	 return doc;
   }
   
   public Document awaitingMessagesAsXMLDoc(User user){
	   DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
       DocumentBuilder dBuilder = null;
		 try {
			  dBuilder = dbFactory.newDocumentBuilder();
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
       Document doc = dBuilder.newDocument();

       Element root =doc.createElement("chat");
       doc.appendChild(root);
       
       for (ChatMessage cm : user.getAwaitingMessages()){
    	   Element chatMessage = doc.createElement("chat-message");
       	   chatMessage.setAttribute("username", cm.getUsername());
		   chatMessage.setAttribute("message", cm.getMessage());
		   root.appendChild(chatMessage);
		} 
	return doc;
   }
   
   public void destroy() {
      // do nothing.
   }
   
   public void sendMessagesToOtherUses(String currentUser, ChatMessage chatMessage){
	  for (User user : users){
		  if (currentUser != user.getUsername()){
			  user.recieveMessage(chatMessage); 
		  }
	  } 
   }
}