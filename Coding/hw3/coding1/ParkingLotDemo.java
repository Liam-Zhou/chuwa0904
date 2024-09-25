public class ParkingLotDemo {

   public static void main(String[] args) {
      ParkingLot parkingLot = new ParkingLot(2, 3, 1); // 2 small, 3 medium, 1 large spots

      Vehicle car = new Vehicle("ABC123", VehicleType.CAR);
      Vehicle motorcycle = new Vehicle("XYZ987", VehicleType.MOTORCYCLE);
      Vehicle truck = new Vehicle("TRK456", VehicleType.TRUCK);

      ParkingTicket ticket1 = parkingLot.parkVehicle(car);
      System.out.println("Car parked, ticket number: " + ticket1.getTicketNumber());

      ParkingTicket ticket2 = parkingLot.parkVehicle(motorcycle);
      System.out.println("Motorcycle parked, ticket number: " + ticket2.getTicketNumber());

      ParkingTicket ticket3 = parkingLot.parkVehicle(truck);
      System.out.println("Truck parked, ticket number: " + (ticket3 != null ? ticket3.getTicketNumber() : "No spot available"));

      // Vacate spot
      parkingLot.vacateSpot(ticket1.getTicketNumber());
      System.out.println("Car vacated, ticket number: " + ticket1.getTicketNumber());
   }
}