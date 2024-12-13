package com.registration.student.enums;

public enum CountryCode {
    AUSTRALIA("AU"),
    CANADA("CA"), 
    CHINA("CN"), 
    GREAT_BRITAIN("GB"), 
    INDIA("IN"), 
    UKRAINE("UA"),
    UNITED_STATES("US");

    private String iso2;

    CountryCode(String iso2) {
        this.iso2 = iso2;
    }

    public String getIso2() {
        return this.iso2;
    }

}