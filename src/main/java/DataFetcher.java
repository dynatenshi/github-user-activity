import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/**
 * Class used to fetch data from GitHub API
 * @author DarkSlayer
 */
public final class DataFetcher {
    private static final String API_ENDPOINT = "https://api.github.com/users/%s/events";

    /**
     * Takes username and fetches activity data of that user
     * @param username target user
     * @return parsed {@code JsonArray} or {@code null}
     */
    public static JsonArray fetchActivity(String username) {
        String targetURL = String.format(API_ENDPOINT, username);
        System.out.println("Fetching data from: "+targetURL);

        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(targetURL))
                    .header("Accept","application/vnd.github+json")
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return JsonParser.parseString(response.body()).getAsJsonArray();
            } else if (response.statusCode() == 404){
                System.err.println("Error 404: User not found");
            } else {
                System.err.println("API error: "+response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
