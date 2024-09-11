package Question20;

public class ParkingLot {
    //init parking spot obj arr
    private ParkingSpot[] spots;

    //constructor 
    public ParkingLot(int numSpots){
        //init a new parkingspot
        spots = new ParkingSpot[numSpots];

        //init each parking spot in parking lot
        for(int i=0;i<numSpots;i++){
            spots[i] = new ParkingSpot();
        }
    }

    //method to operate a vehicle
    //operation park
    public boolean parkVehicle(Vehicle vehicle){
        //find the first availble spot in parking lot
        for(ParkingSpot spot:spots){
            if(spot.isAvailable()){
                spot.park(vehicle);
                System.out.println("spot parked with:"+vehicle.licensePlate);
                return true;
            }
        }

        //if no availble spot
        System.out.println("No available spot for vehicle");
        return false;
    }

    //operation leave
    public void leaveVehicle(Vehicle vehicle){
        for(ParkingSpot spot:spots){
            if(!spot.isAvailable()){
                spot.leave();
                System.out.println("Vehicle left "+vehicle.licensePlate);
                break;
            }
        }
    }

    //check how many spot availble
    public int availableSpot(){
        int count=0;
        for(ParkingSpot spot:spots){
            if(spot.isAvailable()){
                count++;
            }
        }
        return count;
    }

}
