package com.selipasha.convertorapp;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class ConvertorApi {
    private final String apiUrl;
    private final String apiKey;

    public ConvertorApi(String apiUrl, String apiKey) {
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
    }

    public ConvertorData convert(String from, String to, int amount) throws IOException {
        String requestUrl = String.format("%s?to=%s&from=%s&amount=%d", apiUrl, to, from, amount);
        String jsonResponse = sendGetRequest(requestUrl);

        if (jsonResponse != null) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonResponse, ConvertorData.class);
        }

        return null;
    }

    private String sendGetRequest(String requestUrl) throws IOException {
        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("apikey", apiKey);

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                return in.lines().collect(Collectors.joining("\n"));
            } finally {
                connection.disconnect();
            }
        } else {
            System.out.println("GET запрос не удался");
        }

        return null;
    }
}