package p.squidgames.webhook;


import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;



public class WebhookUtil {

    private static final Gson GSON = new Gson();

    public static void sendDiscordWebhook(String webhookUrl, DiscordWebhookMessage message) {
        String jsonMessage = GSON.toJson(message);

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.get("application/json"), jsonMessage);

        Request request = new Request.Builder()
                .url(webhookUrl)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Failed to send webhook message. Error: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    System.out.println("Failed to send webhook message. Status code: " + response.code());
                }
            }
        });
    }
}


