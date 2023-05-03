package com.selipasha.convertorapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyAPI {
    private final String apiUrl;
    private final String apiKey;

    public CurrencyAPI(String apiUrl, String apiKey) {
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
    }

    public void convert(String from, String to, int amount) throws IOException {
        String requestUrl = String.format("%s?to=%s&from=%s&amount=%d", apiUrl, to, from, amount);
        sendGetRequest(requestUrl);
    }

    private void sendGetRequest(String requestUrl) throws IOException {
        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Устанавливаем метод запроса
        connection.setRequestMethod("GET");

        // Добавляем необходимые заголовки
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("apikey", apiKey);

        // Получаем код ответа
        int responseCode = connection.getResponseCode();
        System.out.println("Код ответа: " + responseCode);

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

            // Выводим результат
            System.out.println(content);
        } else {
            System.out.println("GET запрос не удался");
        }
    }
}