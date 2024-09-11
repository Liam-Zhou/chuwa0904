package com.hw3.parkinglot;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<Integer, ParkingSpot> map = new HashMap<>();
        ParkingLot parkingLot = new ParkingLot(map);
        // 0-4 are parking spots for compact car
        for (int i = 0; i <= 4; i++) {
            parkingLot.addParkingSpot(i, new ParkingSpot("Compact", false));
        }
        // 5-9 are parking spots for regular car
        for (int i = 5; i <= 9; i++) {
            parkingLot.addParkingSpot(i, new ParkingSpot("Regular", false));
        }
        //10-11 are parking spots for large car
        for (int i = 10; i <= 11; i++) {
            parkingLot.addParkingSpot(i, new ParkingSpot("Large", false));
        }
        CompactCar c0 = new CompactCar("Compact", "AAA000");
        CompactCar c1 = new CompactCar("Compact", "AAA001");
        CompactCar c2 = new CompactCar("Compact", "AAA002");
        CompactCar c3 = new CompactCar("Compact", "AAA003");
        CompactCar c4 = new CompactCar("Compact", "AAA004");
        RegularCar c5 = new RegularCar("Regular", "BBB000");
        RegularCar c6 = new RegularCar("Regular", "BBB001");
        RegularCar c7 = new RegularCar("Regular", "BBB002");
        RegularCar c8 = new RegularCar("Regular", "BBB003");
        RegularCar c9 = new RegularCar("Regular", "BBB004");
        LargeCar c10 = new LargeCar("Large", "CCC000");
        LargeCar c11 = new LargeCar("Large", "CCC001");

        parkingLot.parkingLot.get(0).park(c0);
        parkingLot.parkingLot.get(1).park(c1);
        parkingLot.parkingLot.get(2).park(c2);
        parkingLot.parkingLot.get(3).park(c3);
        parkingLot.parkingLot.get(4).park(c4);
        parkingLot.parkingLot.get(5).park(c5);
        parkingLot.parkingLot.get(6).park(c6);
        parkingLot.parkingLot.get(7).park(c7);
        parkingLot.parkingLot.get(8).park(c8);
        parkingLot.parkingLot.get(9).park(c9);
        parkingLot.parkingLot.get(10).park(c10);
        parkingLot.parkingLot.get(11).park(c11);

        for (int i = 0; i < 12; i++) {
            System.out.println("Spot " + i + ": " + parkingLot.parkingLot.get(i));
        }
    }
}
