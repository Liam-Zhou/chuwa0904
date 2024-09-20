package com.hw3.parkinglot;

import java.util.HashMap;

public class ParkingLot {
    HashMap<Integer, ParkingSpot> parkingLot = new HashMap<>();

    public ParkingLot(HashMap<Integer, ParkingSpot> parkingLot) {
        this.parkingLot = parkingLot;
    }
    public void addParkingSpot(int spotNum, ParkingSpot parkingSpot) {
        this.parkingLot.put(spotNum, parkingSpot);
    }
}
