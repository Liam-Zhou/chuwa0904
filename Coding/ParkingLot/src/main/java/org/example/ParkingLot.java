package org.example;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private String name;
    private Map<String, ParkingSpot> parkingSpots;
    private Map<String, ParkingTicket> activeTickets;
    private final String TICKET_SERIAL = "TICKER_SERIAL";
    private int ticketNumber;
    public ParkingLot(String name) {
        this.name = name;
        this.parkingSpots = new HashMap<>();
        this.activeTickets = new HashMap<>();
        this.ticketNumber = 0;
    }

    public void initializeParkingSpots(int smallSpots, int mediumSpots, int largeSpots) {
        for (int i = 1; i <= smallSpots; i++) {
            String spotId = "S" + i;
            parkingSpots.put(spotId, new ParkingSpot(spotId, ParkingSpotType.SMALL));
        }
        for (int i = 1; i <= mediumSpots; i++) {
            String spotId = "M" + i;
            parkingSpots.put(spotId, new ParkingSpot(spotId, ParkingSpotType.MEDIUM));
        }
        for (int i = 1; i <= largeSpots; i++) {
            String spotId = "L" + i;
            parkingSpots.put(spotId, new ParkingSpot(spotId, ParkingSpotType.LARGE));
        }
    }

    private void addParkingSpot(ParkingSpot spot) {
        parkingSpots.put(spot.getSpotId(), spot);
    }

    public ParkingSpot findAviailableSpot(Vehicle vehicle) {
        for(ParkingSpot spot : parkingSpots.values()) {
            if(spot.isAvailable() && spot.canFitVehicle(vehicle)) {
                return spot;
            }
        }
        return null;
    }
    // Generate ticker when parking
    public ParkingTicket parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = findAviailableSpot(vehicle);
        if(spot != null) {
            spot.parkVehicle(vehicle);
            String TicketNumber = TICKET_SERIAL + Integer.toString(ticketNumber++);
            ParkingTicket ticket = new ParkingTicket(TicketNumber, vehicle);
            activeTickets.put(vehicle.getLicensePlate(), ticket);
            System.out.println("Vehicle parked at spot: " + spot.getSpotId());
            System.out.println("Ticket Generated: " + ticket.getTicketId());
            return ticket;
        }
        System.out.println("No available spot for vehicle type: " + vehicle.getVehicleType());
        return null;
    }

    // Calculate the ticket price when move out
    public double removeVehicleFromSpot(String licesePlate) {
        ParkingTicket ticket = activeTickets.get(licesePlate);
        ParkingSpot spot = parkingSpots.values().stream()
                .filter(s -> s.getParkedVehicle() != null && s.getParkedVehicle().getLicensePlate().equals(ticket.getVehicle().getLicensePlate()))
                .findFirst()
                .orElse(null);
        if(spot != null && !spot.isAvailable()) {
            spot.removeVehicle();
            double ticketFee = ticket.endParking();
            activeTickets.remove(licesePlate);
            System.out.println("Vehicle removed from spot: " + spot.getSpotId());
            return ticketFee;
        }
        System.out.println("No vehicle found with license plate: " + licesePlate);
        return 0;
    }
}
