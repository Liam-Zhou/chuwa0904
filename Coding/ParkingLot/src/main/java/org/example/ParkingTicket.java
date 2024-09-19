package org.example;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ParkingTicket
{
    private String ticketId;
    private Vehicle vehicle;
    private LocalDateTime parkingTime;
    private LocalDateTime exitTime;
    private double parkingFee;

    public ParkingTicket(String ticketId, Vehicle vehicle) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.parkingTime = LocalDateTime.now();
    }

    public double endParking() {
        this.exitTime = LocalDateTime.now();
        this.parkingFee = calculateParkingFee();

        return parkingFee;
    }

    private double calculateParkingFee() {
        long hoursParked = ChronoUnit.SECONDS.between(parkingTime, exitTime);
        double rate = switch (vehicle.getVehicleType()) {
            case BIKE -> 5;  // Example rate
            case CAR -> 10;  // Example rate
            case TRUCK -> 15;
            default -> 0;  // Example rate
        };

        System.out.println("Parking Time: " + hoursParked + " Seconds");
        System.out.println("Parking Fee: "  + rate * hoursParked);
        return rate * hoursParked;
    }

    public double getParkingFee() {
        return parkingFee;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }
}
