package Question20;

public class ParkingMain {
    public static void main(String[] args) {
        //create a parkinglot with 5 spots
        ParkingLot parkingLot = new ParkingLot(5);

        //creat vehicles
        Vehicle car1 = new Vehicle("PLATE123",2);
        Vehicle bike1 = new Vehicle("PLATE456", 1);

        //park
        parkingLot.parkVehicle(car1);
        parkingLot.parkVehicle(bike1);

        //check how many spots left
        System.out.println("Availble spot:"+parkingLot.availableSpot());

        //leave
        parkingLot.leaveVehicle(bike1);

        //check spots after left the bike
        System.out.println("Availble spot:"+parkingLot.availableSpot());
    }
}
