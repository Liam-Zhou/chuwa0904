package org.example;

public class Main {
    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot(2);

        Car car1 = new Car("CAR123");
        Car car2 = new Car("CAR456");

        lot.parkVehicle(car1);  // Car CAR123 parked.
        lot.parkVehicle(car2);  // Car CAR456 parked.

        lot.parkVehicle(new Car("CAR789"));  // No available spots for car CAR789

        lot.leaveVehicle(car1);  // Car CAR123 left the spot.
        lot.leaveVehicle(car2);  // Car CAR456 left the spot.

    }
}