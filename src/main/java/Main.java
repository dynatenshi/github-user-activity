import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java github-activity <username>");
            return;
        }
        System.out.println("Username:" + args[0]);
        JsonArray data = DataFetcher.fetchActivity(args[0]);

        if (data == null) {return;}

        displayActivity(data);
    }

    public static void displayActivity(JsonArray data) {
        if (data.isEmpty()) {
            System.out.println("No recent public activity for this user in the last 90 days");
            return;
        }

        for (JsonElement element : data) {
            JsonObject event = element.getAsJsonObject();
            String type = event.get("type").getAsString();
            String repoName = event.get("repo").getAsJsonObject().get("name").getAsString();

            switch (type) {
                case "PushEvent":
                    int commitCount = event.get("payload").getAsJsonObject().get("size").getAsInt();
                    System.out.println("Pushed " + commitCount + " commit(s) to " + repoName);
                    break;
                case "IssuesEvent":
                    System.out.println("Opened an issue in " + repoName);
                    break;
                case "WatchEvent":
                    System.out.println("Starred " + repoName);
                    break;
                default:
                    System.out.println("Performed " + type + " on " + repoName);
                    break;
            }
        }
    }
}
