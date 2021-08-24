package database;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

import java.util.HashMap;
import java.util.Map;

public class AlexaSessionDynamoDBHandler {

    public static void saveSessionAttributes(HandlerInput input, String key, String value) {
        try {
            Map<String, Object> persistentAttributes = new HashMap<>(input.getAttributesManager().getPersistentAttributes());
            persistentAttributes.put(key, value);
            input.getAttributesManager().setPersistentAttributes(persistentAttributes);
            input.getAttributesManager().savePersistentAttributes();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            throw ex;
        }
    }
}
