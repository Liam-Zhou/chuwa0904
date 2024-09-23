import java.util.Date;

public class ParkingTicket {
   private String ticketNumber;
   private Date issuedAt;
   private Vehicle vehicle;
   private ParkingSpot spot;

   public ParkingTicket(String ticketNumber, Vehicle vehicle, ParkingSpot spot) {
      this.ticketNumber = ticketNumber;
      this.issuedAt = new Date();
      this.vehicle = vehicle;
      this.spot = spot;
   }

   public String getTicketNumber() {
      return ticketNumber;
   }

   public Date getIssuedAt() {
      return issuedAt;
   }

   public Vehicle getVehicle() {
      return vehicle;
   }

   public ParkingSpot getSpot() {
      return spot;
   }
}
