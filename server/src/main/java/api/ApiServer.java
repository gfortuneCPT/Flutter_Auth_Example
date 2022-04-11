package api;

import io.javalin.Javalin;
import io.javalin.plugin.openapi.OpenApiOptions;
import io.javalin.plugin.openapi.OpenApiPlugin;
import io.javalin.plugin.openapi.ui.ReDocOptions;
import io.javalin.plugin.openapi.ui.SwaggerOptions;
import io.swagger.v3.oas.models.info.Info;

import java.security.NoSuchAlgorithmException;

public class ApiServer {
    private final Javalin server;

    public ApiServer() {

        server = Javalin.create(
                config -> {
                    config.defaultContentType = "application/json";
                    config.enableCorsForAllOrigins();
                    config.registerPlugin(new OpenApiPlugin(getOpenApiOptions()));
                }
        );
        this.server.get("/login", ApiHandler::login);
    }

    private OpenApiOptions getOpenApiOptions() {
        Info info = new Info().version("1.0").description("User API");
        return new OpenApiOptions(info)
                .activateAnnotationScanningFor("io.javalin.example.java")
                .path("/swagger-docs") // endpoint for OpenAPI json
                .swagger(new SwaggerOptions("/swagger-ui")) // endpoint for swagger-ui
                .reDoc(new ReDocOptions("/redoc"));
    }


    public void start(int port) {
        this.server.start(port);
    }

    public void stop() {
        this.server.stop();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        ApiServer server = new ApiServer();

//        DatabaseController databaseController = new DatabaseController();
//        databaseController.createTables();
        server.start(5000);
    }
}
