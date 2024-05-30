package utils;

import io.github.pixee.security.BoundedLineReader;
import java.io.BufferedReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;

public class GetJson {

  private static final Logger log = LogManager.getLogger(GetJson.class);

  public static JSONArray getJssonArrayFromPost(BufferedReader reader) {
    StringBuffer jb = new StringBuffer();
    String line = null;
    // Buffer entire JSON array
    try {
      while ((line = BoundedLineReader.readLine(reader, 5_000_000)) != null) {
        jb.append(line);
      }
    } catch (Exception e) {
      log.error("Unable to buffer JSON array from request.");
    }
    try {
      JSONArray jsonArray = new JSONArray(jb.toString());
      return jsonArray;
    } catch (Exception e) {
      // crash and burn
      log.error("Unable to Extract or Parse JSONObject from JSON Buffer");
    }
    return null;
  }
}
