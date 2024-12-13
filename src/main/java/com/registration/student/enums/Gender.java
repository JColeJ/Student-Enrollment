package com.registration.student.enums;

import com.registration.student.util.StudentUtil;

public enum Gender {

    MALE(StudentUtil.MALE_SYMBOL),
    FEMALE(StudentUtil.FEMALE_SYMBOL),
    UNSPECIFIED(StudentUtil.UNSPECIFIED_SYMBOL);

    private String symbol;

    Gender(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public boolean isMale() {
        return StudentUtil.MALE_SYMBOL.equals(this.symbol);
    }

    public boolean isFemale() {
        return StudentUtil.FEMALE_SYMBOL.equals(this.symbol);
    }

    public boolean isUnspecified() {
        return StudentUtil.UNSPECIFIED_SYMBOL.equals(this.symbol);
    }

    public static Gender fromSymbol(String symbol) {
        return switch (symbol) {
            case StudentUtil.MALE_SYMBOL -> MALE;
            case StudentUtil.FEMALE_SYMBOL -> FEMALE;
            default -> UNSPECIFIED;
        };
    }
}