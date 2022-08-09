package webserviceautomation;

import java.net.URLEncoder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
Unirest.setTimeouts(0, 0);
HttpResponse<String> response = Unirest.post("https://api.trello.com/1/boards/?&key=7f2ef6fe0ac6047dfc2737156b1f2c0c&token=7e7ee6bc6cfe1ccd757e6504ec4f025e58c49802eb998f1c1eb6a9be319dc9d1")
  .header("Content-Type", "application/json")
  .header("Cookie", "preAuthProps=s%3A62f2551ccc08fb26bbb54c42%3AisEnterpriseAdmin%3Dfalse.im5mJb7oJDYnZakeYRGHrsNUVxv4MMbvBPs5YaPo1ho")
  .body("{\r\n    \"name\": \"Burak\",\r\n    \"prefs_permissionLevel\": \"Public\"\r\n}")
  .asString();

        
    }
}
