package Question20;

public class ParkingSpot {
    //the parked vehicle
    private Vehicle vehicle;

    public boolean isAvailable(){
        return vehicle == null;
    }

    //operation park
    public void park(Vehicle vehicle){
        this.vehicle = vehicle;

    }

    //operation leave
    public void leave(){
        this.vehicle = null;
    }

}
