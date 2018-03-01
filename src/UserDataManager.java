import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 * Handles the user data stored in JSON
 * @author donovanramsey
 *
 */
public class UserDataManager {
	
	public static String JSON_FILE_PATH = "../../WebContent/ChillHub/html/ChillHub/json/user_data.json";
	
	public JSONObject jsonData; 
	
	JSONParser parser = new JSONParser();
	
	public UserDataManager() throws FileNotFoundException, IOException, ParseException {
		//parse JSON file into object
		jsonData = (JSONObject) parser.parse(new FileReader(JSON_FILE_PATH));
	}
	
	public boolean checkIfUserExists(String username) {
		JSONArray users = (JSONArray) jsonData.get("users");
		for (Object o : users){
			JSONObject user = (JSONObject) o;
			if (user.get("username").equals(username)){
				return true;
			}
		}
		return false; 
	}
	
	/**
	 * add username to json data 
	 */
	public void addNewUser(String username, String password) {
		JSONArray users = (JSONArray) jsonData.get("users");
		JSONObject newObject = new JSONObject();
	    newObject.put("username", username);
	    newObject.put("password", password);
	    users.add(newObject);
	    
	    writeToJSONFile(jsonData);
	    
	}
	
	/**
	 * Overwrite the previous json file
	 * @param o
	 */
	public void writeToJSONFile(JSONObject o) {
		try (FileWriter file = new FileWriter(JSON_FILE_PATH)) {
			
            file.write(o.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("problem writing to JSON file: " + JSON_FILE_PATH);
        }
	}
	
}
