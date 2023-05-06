package com.selipasha.convertorapp;

public class Convertor {
    public static void main(String[] args) {
        String apiUrl = "https://api.apilayer.com/exchangerates_data/convert";
        String apiKey = "bFRqe0T8GedPrgRITO4o2P8nhIUarzD8";
        new ConvertorGui(apiUrl, apiKey);
    }
}