public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(3, 30);  // 3 levels, 30 spots per level

        Vehicle car = new Car("CAR123");
        Vehicle motorcycle = new Motorcycle("BIKE123");
        Vehicle truck = new Truck("TRUCK123");

        parkingLot.parkVehicle(car);
        parkingLot.parkVehicle(motorcycle);
        parkingLot.parkVehicle(truck);

        parkingLot.displayAvailableSpots();

        parkingLot.removeVehicle(car);
        parkingLot.displayAvailableSpots();
    }
}
