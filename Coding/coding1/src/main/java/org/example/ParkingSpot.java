package coding1.src.main.java.org.example;

public class ParkingSpot {
    private boolean isOccupied;

    public ParkingSpot() {
        this.isOccupied = false;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void markAsOccupied() {
        isOccupied = true;
    }

    public void markAsUnoccupied() {
        isOccupied = false;
    }


}
