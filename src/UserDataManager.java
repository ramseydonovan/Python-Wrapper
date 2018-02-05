import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 * Handles the user data stored in JSON
 * @author donovanramsey
 *
 */
public class UserDataManager {
	
	public static String JSON_FILE_PATH = "../../WebContent/ChillHub/html/ChillHub/json/user_data.json";
	public JSONArray jsonData; 
	JSONParser parser = new JSONParser();
	
	public UserDataManager() throws FileNotFoundException, IOException, ParseException {
		//parse JSON file into object
		jsonData = (JSONArray) parser.parse(new FileReader(JSON_FILE_PATH));
	}
}
