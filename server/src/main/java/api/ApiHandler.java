package api;

import com.fasterxml.jackson.databind.JsonNode;
import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import security.EncryptionHandler;

import java.util.Map;

public class ApiHandler {

    public static void login(Context context) {
        JsonNode json = context.bodyAsClass(JsonNode.class);
        String username = json.get("username").asText();
        String password = json.get("password").asText();

    }

    public static void getPublicKey(Context context) {
        context.status(HttpCode.OK);
        context.json(Map.of("publicKey", EncryptionHandler.getPublicKey().toString()));
    }
}
