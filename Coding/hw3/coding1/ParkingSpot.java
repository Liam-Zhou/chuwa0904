public class ParkingSpot {
   private int spotNumber;
   private ParkingSpotType spotType;
   private boolean isOccupied;
   private Vehicle currentVehicle;

   public ParkingSpot(int spotNumber, ParkingSpotType spotType) {
      this.spotNumber = spotNumber;
      this.spotType = spotType;
      this.isOccupied = false;
      this.currentVehicle = null;
   }

   public boolean isAvailable() {
      return !isOccupied;
   }

   public boolean canFitVehicle(Vehicle vehicle) {
      if (this.spotType == ParkingSpotType.SMALL && vehicle.getType() == VehicleType.MOTORCYCLE) {
         return true;
      } else if (this.spotType == ParkingSpotType.MEDIUM && (vehicle.getType() == VehicleType.CAR || vehicle.getType() == VehicleType.MOTORCYCLE)) {
         return true;
      } else if (this.spotType == ParkingSpotType.LARGE) {
         return true;
      }
      return false;
   }

   public void park(Vehicle vehicle) {
      this.currentVehicle = vehicle;
      this.isOccupied = true;
   }

   public void vacate() {
      this.currentVehicle = null;
      this.isOccupied = false;
   }

   public int getSpotNumber() {
      return spotNumber;
   }
}
