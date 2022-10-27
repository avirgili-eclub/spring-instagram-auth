package virtech.com.polacore.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Util {
    static final Logger logger = LoggerFactory.getLogger(Util.class);

    public static JSONObject getJsonFromObject(Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JSR310Module());
            return (JSONObject) JSONValue.parse(mapper.writeValueAsString(object));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static JSONObject getJsonFromString(String stringObject) {
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(stringObject);
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static Object convertStringJsonToObject(String chargeJson, Class type) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JSR310Module());
            mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
            return mapper.readValue(chargeJson, type);
        } catch (IllegalArgumentException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}

