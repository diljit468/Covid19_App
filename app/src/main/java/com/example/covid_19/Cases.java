package com.example.covid_19;

public class Cases {

    String country;
    int allCases;


    public Cases(String country, int allCases) {
        this.country = country;
        this.allCases = allCases;

    }

    public String getCountry() {
        return country;
    }

    public int getAllCases() {
        return allCases;
    }
}
