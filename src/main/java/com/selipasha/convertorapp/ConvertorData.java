package com.selipasha.convertorapp;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ConvertorData {
    @JsonProperty("success")
    private boolean success;

    @JsonProperty("query")
    private Query query;

    @JsonProperty("info")
    private Info info;

    @JsonProperty("date")
    private String date;

    @JsonProperty("result")
    private float result;

    public boolean isSuccess() {
        return success;
    }

    public Query getQuery() {
        return query;
    }

    public Info getInfo() {
        return info;
    }

    public String getDate() {
        return date;
    }

    public float getResult() {
        return result;
    }

    public static class Query {
        @JsonProperty("from")
        private String from;

        @JsonProperty("to")
        private String to;

        @JsonProperty("amount")
        private float amount;

        // Геттеры для полей
        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }

        public float getAmount() {
            return amount;
        }
    }

    public static class Info {
        @JsonProperty("timestamp")
        private long timestamp;

        @JsonProperty("rate")
        private float rate;

        // Геттеры для полей
        public long getTimestamp() {
            return timestamp;
        }

        public float getRate() {
            return rate;
        }
    }
}