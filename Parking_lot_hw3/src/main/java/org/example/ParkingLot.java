package org.example;

public class ParkingLot {
    private ParkingSpot[] carSpots;

    public ParkingLot(int numCarSpots) {
        carSpots = new CarSpot[numCarSpots];
        for (int i = 0; i < numCarSpots; i++) {
            carSpots[i] = new CarSpot();
        }
    }

    public boolean parkVehicle(Car car) {
        for (ParkingSpot spot : carSpots) {
            if (spot.isAvailable() && spot.canFitVehicle(car)) {
                spot.park(car);
                System.out.println("Car " + car.getLicensePlate() + " parked.");
                return true;
            }
        }
        System.out.println("No available spots for car " + car.getLicensePlate());
        return false;
    }

    public void leaveVehicle(Car car) {
        for (ParkingSpot spot : carSpots) {
            if (spot.getVehicle() != null && spot.getVehicle().getLicensePlate().equals(car.getLicensePlate())) {
                spot.leave();
                System.out.println("Car " + car.getLicensePlate() + " left the spot.");
                return;
            }
        }
        System.out.println("Car " + car.getLicensePlate() + " was not found.");
    }
}