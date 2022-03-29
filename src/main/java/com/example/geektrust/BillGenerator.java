package com.example.geektrust;

import com.example.geektrust.enums.ApartmentType;
import com.example.geektrust.model.TankWaterSlab;
import com.example.geektrust.model.WaterBill;

public class BillGenerator {
    ApartmentType apartmentType;
    Ratio ratio;
    int guests;

    private static BillGenerator billGenerator = null;

    public static BillGenerator init(ApartmentType apartmentType, Ratio ratio) {
        if (billGenerator != null) {
            return billGenerator;
        }
        billGenerator = new BillGenerator(apartmentType, ratio);
        return billGenerator;
    }

    private BillGenerator(ApartmentType apartmentType, Ratio ratio) {
        this.apartmentType = apartmentType;
        this.ratio = ratio;
        this.guests = 0;
    }

    public void addGuests(int guests) {
        this.guests += guests;
    }

    public WaterBill generateBill() {
        double apartmentConsumption = (apartmentType == ApartmentType.TWO_BHK) ? (3 * 10 * 30) : (5 * 10 * 30);
        double borewellWater = apartmentConsumption * ratio.borewellWater;
        double corporationWater = apartmentConsumption * ratio.corporationWater;
        double apartmentBill = borewellWater * Prices.BOREWELL_WATER + corporationWater * Prices.CORPORATION_WATER;

        double guestsConsumption = guests * 10 * 30;
        double totalBill = apartmentBill;
        double totalConsumption = apartmentConsumption + guestsConsumption;
        for (int i = 0; i < Prices.tankWaterSlabs.size(); i++) {
            TankWaterSlab tankWaterSlab = Prices.tankWaterSlabs.get(i);
            totalBill += Math.min(tankWaterSlab.max, guestsConsumption) * tankWaterSlab.price;
            if (guestsConsumption <= tankWaterSlab.max) {
                break;
            }
            guestsConsumption -= tankWaterSlab.max;
        }
        return new WaterBill(Math.round(totalConsumption), Math.round(totalBill));
    }
}
