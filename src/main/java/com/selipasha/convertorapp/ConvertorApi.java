package com.selipasha.convertorapp;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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

        // Устанавливаем метод запроса
        connection.setRequestMethod("GET");

        // Добавляем необходимые заголовки
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("apikey", apiKey);

        // Получаем код ответа
        int responseCode = connection.getResponseCode();

        // Читаем ответ
        if (responseCode == HttpURLConnection.HTTP_OK) { // Успешный ответ
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            // Закрываем соединения
            in.close();
            connection.disconnect();

            return content.toString();
        } else {
            System.out.println("GET запрос не удался");
        }

        return null;
    }
}