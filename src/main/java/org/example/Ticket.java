package org.example;

public class Ticket {
    private String ticketId;
    private String passengerName;
    private String busId;
    private int seatNumber;

    public Ticket(String ticketId, String passengerName, String busId, int seatNumber) {
        this.ticketId = ticketId;
        this.passengerName = passengerName;
        this.busId = busId;
        this.seatNumber = seatNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getBusId() {
        return busId;
    }

    public void displayTicket() {
        System.out.println("Vé xe: ");
        System.out.println("ID: " + ticketId);
        System.out.println("Hành khách: " + passengerName);
        System.out.println("Xe: " + busId);
        System.out.println("Ghế: " + seatNumber);
    }
}
