package com.selipasha.convertorapp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Convertor {
    public static void main(String[] args) {
        try {
            String apiUrl = "https://api.apilayer.com/exchangerates_data/convert";
            String apiKey = "bFRqe0T8GedPrgRITO4o2P8nhIUarzD8";

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Какую валюту будете менять? ");
            String fromCurrency = reader.readLine().toUpperCase();

            System.out.print("На какую валюту будете менять? ");
            String toCurrency = reader.readLine().toUpperCase();

            System.out.print("Сколько хотите поменять? ");
            int amount = Integer.parseInt(reader.readLine());

            ConvertorApi converter = new ConvertorApi(apiUrl, apiKey);
//            long startTime = System.nanoTime();
            ConvertorData data = converter.convert(fromCurrency, toCurrency, amount);
//            long endTime = System.nanoTime();

            System.out.println("Результат: " + data.getResult());

//            long duration = endTime - startTime;
//            System.out.println("Время выполнения запроса: " + duration / 1_000_000 + " мс");



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}