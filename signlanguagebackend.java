import static spark.Spark.*;

public class SignLanguageBackend {
    public static void main(String[] args) {
        // Enable CORS
        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Request-Method", "GET,POST");
            response.header("Access-Control-Allow-Headers", "*");
            response.type("application/json");
        });

        // Simple test endpoint
        get("/api/test", (req, res) -> "{\"message\": \"Backend is working!\"}");

        // Sign language conversion endpoint
        post("/api/convert", (req, res) -> {
            // In a real app, you would parse the request body properly
            String gesture = req.queryParams("gesture");
            
            // Simple conversion logic
            String result;
            switch(gesture.toUpperCase()) {
                case "A": result = "A"; break;
                case "B": result = "B"; break;
                case "C": result = "C"; break;
                case "L": result = "L"; break;
                case "V": result = "V"; break;
                case "Y": result = "Y"; break;
                default: result = "?"; break;
            }
            
            return "{\"result\": \"" + result + "\"}";
        });
    }
}