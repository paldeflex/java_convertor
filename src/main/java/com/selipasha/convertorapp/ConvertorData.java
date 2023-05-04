package com.selipasha.convertorapp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ConvertorData {
    @JsonProperty("result")
    private float result;

    public float getResult() {
        return result;
    }
}

