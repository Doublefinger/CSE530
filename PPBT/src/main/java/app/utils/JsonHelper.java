package app.utils;

import org.codehaus.jackson.map.ObjectMapper;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Doublefinger on 4/25/16.
 */
public class JsonHelper {
    public static Map toMap(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String fromMap(Map map) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(map);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
