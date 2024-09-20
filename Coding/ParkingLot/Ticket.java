package ParkingLot;

import java.util.Date;

class ParkingTicket {
    private final Date entryTime;
    private Date exitTime;

    public ParkingTicket(String ticketId, Vehicle vehicle) {
        this.entryTime = new Date();  // Timestamp when the vehicle enters
    }

    public void setExitTime() {
        this.exitTime = new Date();  // Timestamp when the vehicle leaves
    }

    public long getParkingDuration() {
        if (exitTime == null) {
            return -1;  // Exit time not set
        }
        return (exitTime.getTime() - entryTime.getTime()) / 1000;  // Duration in seconds
    }
}

class Payment {
    private static final double RATE_PER_HOUR = 2.5;  // Example rate

    public static double calculatePayment(ParkingTicket ticket) {
        long durationInHours = (ticket.getParkingDuration() / 3600);
        return durationInHours * RATE_PER_HOUR;
    }
}

