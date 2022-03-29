package com.example.geektrust.enums;

import java.util.Arrays;

public enum ApartmentType {
    TWO_BHK(2),
    THREE_BHK(3);

    private int id;

    ApartmentType(int id) {
        this.id = id;
    }

    int getId() {
        return id;
    }

    public static ApartmentType getApartmentType(int id) {
        return Arrays.stream(ApartmentType.values()).filter(apartmentType -> apartmentType.getId() == id).findFirst().orElse(null);
    }
}
