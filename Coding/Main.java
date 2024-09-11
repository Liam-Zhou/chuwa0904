package org.example;

enum VehicleType {
    MOTORCYCLE, CAR, TRUCK;
}

abstract class Vehicle {
    private String licensePlate;
    private org.example.VehicleType type;

    public Vehicle(String licensePlate, org.example.VehicleType type) {
        this.licensePlate = licensePlate;
        this.type = type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public org.example.VehicleType getType() {
        return type;
    }
}

class Motorcycle extends org.example.Vehicle {
    public Motorcycle(String licensePlate) {
        super(licensePlate, org.example.VehicleType.MOTORCYCLE);
    }
}

class Car extends org.example.Vehicle {
    public Car(String licensePlate) {
        super(licensePlate, org.example.VehicleType.CAR);
    }
}

class Truck extends org.example.Vehicle {
    public Truck(String licensePlate) {
        super(licensePlate, org.example.VehicleType.TRUCK);
    }
}

class ParkingSpace {
    private int spaceId;
    private org.example.VehicleType spaceType;
    private boolean isOccupied;
    private org.example.Vehicle parkedVehicle;

    public ParkingSpace(int id, org.example.VehicleType type) {
        this.spaceId = id;
        this.spaceType = type;
        this.isOccupied = false;
    }

    public boolean canPark(org.example.Vehicle vehicle) {
        return !isOccupied && vehicle.getType() == spaceType;
    }

    public boolean park(org.example.Vehicle vehicle) {
        if (canPark(vehicle)) {
            this.parkedVehicle = vehicle;
            this.isOccupied = true;
            return true;
        }
        return false;
    }

    public void unpark() {
        this.parkedVehicle = null;
        this.isOccupied = false;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public int getSpaceId() {
        return spaceId;
    }

    public org.example.Vehicle getParkedVehicle() {
        return parkedVehicle;
    }
}

class ParkingLot {
    private org.example.ParkingSpace[] parkingSpaces;

    public ParkingLot(int totalSpaces) {
        parkingSpaces = new org.example.ParkingSpace[totalSpaces];

        for (int i = 0; i < totalSpaces; i++) {
            if (i < totalSpaces / 3) {
                parkingSpaces[i] = new org.example.ParkingSpace(i, org.example.VehicleType.MOTORCYCLE);
            } else if (i < 2 * totalSpaces / 3) {
                parkingSpaces[i] = new org.example.ParkingSpace(i, org.example.VehicleType.CAR);
            } else {
                parkingSpaces[i] = new org.example.ParkingSpace(i, org.example.VehicleType.TRUCK);
            }
        }
    }

    public org.example.ParkingTicket parkVehicle(org.example.Vehicle vehicle) {
        for (org.example.ParkingSpace space : parkingSpaces) {
            if (space.canPark(vehicle)) {
                space.park(vehicle);
                return new org.example.ParkingTicket(space.getSpaceId(), vehicle.getLicensePlate());
            }
        }
        System.out.println("No available space for vehicle type: " + vehicle.getType());
        return null;
    }

    public void unparkVehicle(org.example.ParkingTicket ticket) {
        int spaceId = ticket.getSpaceId();
        if (parkingSpaces[spaceId].isOccupied()) {
            parkingSpaces[spaceId].unpark();
            System.out.println("Vehicle unparked from space " + spaceId);
        } else {
            System.out.println("No vehicle is parked at space " + spaceId);
        }
    }

    public void displayParkingStatus() {
        for (org.example.ParkingSpace space : parkingSpaces) {
            if (space.isOccupied()) {
                System.out.println("Space " + space.getSpaceId() + " is occupied by " + space.getParkedVehicle().getLicensePlate());
            } else {
                System.out.println("Space " + space.getSpaceId() + " is free");
            }
        }
    }
}

class ParkingTicket {
    private int spaceId;
    private String vehicleLicensePlate;

    public ParkingTicket(int spaceId, String vehicleLicensePlate) {
        this.spaceId = spaceId;
        this.vehicleLicensePlate = vehicleLicensePlate;
    }

    public int getSpaceId() {
        return spaceId;
    }

    public String getVehicleLicensePlate() {
        return vehicleLicensePlate;
    }
}

public class Main {
    public static void main(String[] args) {
        org.example.ParkingLot parkingLot = new org.example.ParkingLot(10);

        org.example.Vehicle car1 = new org.example.Car("ABC123");
        org.example.Vehicle motorcycle1 = new org.example.Motorcycle("XYZ789");
        org.example.Vehicle truck1 = new org.example.Truck("TRK567");

        org.example.ParkingTicket carTicket = parkingLot.parkVehicle(car1);
        org.example.ParkingTicket motorcycleTicket = parkingLot.parkVehicle(motorcycle1);
        org.example.ParkingTicket truckTicket = parkingLot.parkVehicle(truck1);

        parkingLot.displayParkingStatus();

        parkingLot.unparkVehicle(carTicket);
        parkingLot.unparkVehicle(motorcycleTicket);

        parkingLot.displayParkingStatus();
    }
}
