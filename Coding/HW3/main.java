public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(2, 10);

        Vehicle car = new Car("CAR-123");
        Vehicle motorcycle = new Motorcycle("BIKE-456");
        Vehicle truck = new Truck("TRUCK-789");

        parkingLot.parkVehicle(car);        // Output: Car parked.
        parkingLot.parkVehicle(motorcycle); // Output: Motorcycle parked.
        parkingLot.parkVehicle(truck);      // Output: Truck parked.

        parkingLot.removeVehicle(car);      // Output: Car removed from parking.
    }
}
