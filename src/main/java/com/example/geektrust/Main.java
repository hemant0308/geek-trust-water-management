package com.example.geektrust;

import com.example.geektrust.enums.ApartmentType;
import com.example.geektrust.enums.Command;
import com.example.geektrust.model.WaterBill;

import javax.inject.Inject;
import java.io.*;
import java.util.Scanner;

public class Main {

    public static InputStream inputStream = System.in;
    public static PrintStream outputStream = System.out;

    public static void main(String[] args) throws Exception {
        if(args.length > 0) {
            inputStream = new FileInputStream(new File(args[0]));
        }
        Scanner scanner = new Scanner(inputStream);
        System.setOut(outputStream);
        BillGenerator billGenerator = null;
        while (true && scanner.hasNext()) {
            Command command = Command.valueOf(scanner.next());
            switch (command) {
                case ALLOT_WATER:
                    int apartmentTypeId = scanner.nextInt();
                    ApartmentType apartmentType = ApartmentType.getApartmentType(apartmentTypeId);
                    String ratioString = scanner.next();
                    Ratio ratio = new Ratio(ratioString);
                    billGenerator = BillGenerator.init(apartmentType, ratio);
                    break;
                case ADD_GUESTS:
                    int guestsCount = scanner.nextInt();
                    if (billGenerator != null)
                        billGenerator.addGuests(guestsCount);
                    else
                        throw new Exception("Apartment type must be provided before adding guests");
                    break;
                case BILL:
                    if (billGenerator != null) {
                        WaterBill bill = billGenerator.generateBill();
                        System.out.println((long) bill.consumption + " " + (long)bill.amount);
                    }
                    break;
            }
        }
    }
}
