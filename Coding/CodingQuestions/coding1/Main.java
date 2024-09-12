package Coding.CodingQuestions.coding1;

public class Main {
    public static void main(String[] args) {
        // Create a parking lot with 3 small, 3 compact, and 2 large spots
        ParkingLot parkingLot = new ParkingLot(3, 3, 2);

        // Create vehicles
        Vehicle car = new Car("CAR-1234");
        Vehicle bike = new Bike("BIKE-5678");
        Vehicle truck = new Truck("TRUCK-9999");

        // Park vehicles
        parkingLot.parkVehicle(car);
        parkingLot.parkVehicle(bike);
        parkingLot.parkVehicle(truck);

        // Display available spots
        parkingLot.displayAvailableSpots();

        // Remove a vehicle
        parkingLot.removeVehicle(bike);

        // Display available spots again
        parkingLot.displayAvailableSpots();
    }
}
