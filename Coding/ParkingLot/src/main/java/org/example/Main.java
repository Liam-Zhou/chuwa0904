package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLotA = new ParkingLot("Main");
        parkingLotA.initializeParkingSpots(3,3,3);

        Vehicle bike  = new Bike("aaaaaa");
        Vehicle car   = new Car("bbbbb");
        Vehicle truck = new Truck("ccccc");

        parkingLotA.parkVehicle(bike);
        parkingLotA.parkVehicle(car);
        parkingLotA.parkVehicle(truck);

        try {
            System.out.println("Processing ticket...");
            Thread.sleep(2000);  // Wait for 3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();  // Handle the interrupted exception
        }

        double carFee  = parkingLotA.removeVehicleFromSpot(bike.getLicensePlate());
        double bikeFee = parkingLotA.removeVehicleFromSpot(car.getLicensePlate());
    }
}