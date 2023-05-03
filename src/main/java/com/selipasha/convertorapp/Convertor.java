package com.selipasha.convertorapp;
import java.io.IOException;

public class Convertor {
    public static void main(String[] args) {
        try {
            String apiUrl = "https://api.apilayer.com/exchangerates_data/convert";
            String apiKey = "bFRqe0T8GedPrgRITO4o2P8nhIUarzD8";

            CurrencyAPI converter = new CurrencyAPI(apiUrl, apiKey);
            converter.convert("USD", "RUB", 100);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}