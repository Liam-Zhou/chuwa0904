package Coding.CodingQuestions.coding1;

import java.util.Date;
import java.util.UUID;

public class ParkingTicket {
    private String ticketNumber;
    private Date entryTime;

    public ParkingTicket() {
        this.ticketNumber = UUID.randomUUID().toString();
        this.entryTime = new Date();
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public Date getEntryTime() {
        return entryTime;
    }
}
