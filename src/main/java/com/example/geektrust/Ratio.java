package com.example.geektrust;

public class Ratio {
    double corporationWater;
    double borewellWater;

    public Ratio(String ratioString) {
        String ratioTokens[] = ratioString.split(":");
        int corporationRatio = Integer.parseInt(ratioTokens[0]);
        int borewellRatio = Integer.parseInt(ratioTokens[1]);
        corporationWater = corporationRatio /(1.0 * (corporationRatio + borewellRatio));
        borewellWater = borewellRatio / (1.0 * (borewellRatio + corporationRatio));
    }
}
