package com.example.covid_19;

import org.json.JSONArray;

import java.util.ArrayList;

public class ContinentModel {
    String name;
    JSONArray array;

    public ContinentModel(String name, JSONArray array) {
        this.name = name;
        this.array = array;
    }

    public String getName() {
        return name;
    }

    public JSONArray getArray() {
        return array;
    }
}
