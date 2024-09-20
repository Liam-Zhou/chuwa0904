public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(2, 1, 1);
        Vehicle car1 = new Car("CAR123");
        Vehicle motorcycle1 = new Motorcycle("MOTO456");
        Vehicle truck1 = new Truck("TRUCK789");
        parkingLot.parkVehicle(car1);
        parkingLot.parkVehicle(motorcycle1);
        parkingLot.parkVehicle(truck1);
        Vehicle car2 = new Car("CAR234");
        parkingLot.parkVehicle(car2);
        parkingLot.removeVehicle(car1);
        parkingLot.parkVehicle(car2);
    }
}
