import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
   private Map<Integer, ParkingSpot> parkingSpots;
   private Map<String, ParkingTicket> parkingTickets;

   public ParkingLot(int numSmallSpots, int numMediumSpots, int numLargeSpots) {
      this.parkingSpots = new HashMap<>();
      this.parkingTickets = new HashMap<>();

      int spotNumber = 1;

      // Initialize parking spots
      for (int i = 0; i < numSmallSpots; i++) {
         parkingSpots.put(spotNumber, new ParkingSpot(spotNumber, ParkingSpotType.SMALL));
         spotNumber++;
      }

      for (int i = 0; i < numMediumSpots; i++) {
         parkingSpots.put(spotNumber, new ParkingSpot(spotNumber, ParkingSpotType.MEDIUM));
         spotNumber++;
      }

      for (int i = 0; i < numLargeSpots; i++) {
         parkingSpots.put(spotNumber, new ParkingSpot(spotNumber, ParkingSpotType.LARGE));
         spotNumber++;
      }
   }

   public ParkingTicket parkVehicle(Vehicle vehicle) {
      for (ParkingSpot spot : parkingSpots.values()) {
         if (spot.isAvailable() && spot.canFitVehicle(vehicle)) {
            spot.park(vehicle);
            ParkingTicket ticket = new ParkingTicket("TICKET" + System.currentTimeMillis(), vehicle, spot);
            parkingTickets.put(ticket.getTicketNumber(), ticket);
            return ticket;
         }
      }
      return null; // No available spot
   }

   public void vacateSpot(String ticketNumber) {
      ParkingTicket ticket = parkingTickets.get(ticketNumber);
      if (ticket != null) {
         ticket.getSpot().vacate();
         parkingTickets.remove(ticketNumber);
      }
   }
}
