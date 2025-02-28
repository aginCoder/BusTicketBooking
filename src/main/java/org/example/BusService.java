package org.example;

import java.util.ArrayList;
import java.util.List;

public class BusService {
    private List<Bus> buses = new ArrayList<>();

    public void addBus(Bus bus) {
        buses.add(bus);
    }

    public Bus getBusById(String busId) {
        for (Bus bus : buses) {
            if (bus.getId().equalsIgnoreCase(busId)) {
                return bus;
            }
        }
        return null;
    }

    public Ticket bookTicket(String busId, String passengerName, int seatNumber) {
        Bus bus = getBusById(busId);
        if (bus != null && bus.bookSeat(seatNumber)) {
            return new Ticket("TICKET" + seatNumber, passengerName, busId, seatNumber);
        }
        return null;
    }

    public List<Ticket> bookMultipleTickets(String busId, String customerName, List<Integer> seatNumbers) {
        List<Ticket> tickets = new ArrayList<>();
        Bus bus = getBusById(busId);

        if (bus == null) {
            System.out.println("❌ Không tìm thấy xe với ID: " + busId);
            return tickets;
        }

        List<Integer> successfullyBookedSeats = new ArrayList<>();
        List<Integer> alreadyBookedSeats = new ArrayList<>();

        for (int seatNumber : seatNumbers) {
            if (seatNumber < 1 || seatNumber > bus.getTotalSeats()) {
                System.out.println("❌ Ghế số " + seatNumber + " không hợp lệ!");
                continue;
            }

            if (bus.getSeats()[seatNumber - 1]) {
                alreadyBookedSeats.add(seatNumber);
            } else {
                Ticket ticket = bookTicket(busId, customerName, seatNumber);
                if (ticket != null) {
                    tickets.add(ticket);
                    successfullyBookedSeats.add(seatNumber);
                }
            }
        }

        if (!successfullyBookedSeats.isEmpty()) {
            System.out.println("✅ Đặt vé thành công cho các ghế: " + successfullyBookedSeats);
        }
        if (!alreadyBookedSeats.isEmpty()) {
            System.out.println("❌ Ghế đã được đặt: " + alreadyBookedSeats);
        }

        return tickets;
    }

    public void displayAllBuses() {
        if (buses.isEmpty()) {
            System.out.println("🚍 Không có tuyến xe nào.");
            return;
        }
        System.out.println("🚍 Danh sách các tuyến xe:");
        for (Bus bus : buses) {
            System.out.println("ID Xe: " + bus.getId() + " | Tuyến: " + bus.getRoute());
        }
    }

    public void searchBusByRoute(String route) {
        boolean found = false;
        System.out.println("🔍 Tìm kiếm tuyến xe: " + route);
        for (Bus bus : buses) {
            if (bus.getRoute().equalsIgnoreCase(route)) {
                System.out.println("Xe ID: " + bus.getId() + " | Tuyến: " + bus.getRoute());
                found = true;
            }
        }
        if (!found) {
            System.out.println("❌ Không tìm thấy tuyến xe nào phù hợp!");
        }
    }

    public int getMaxSeats(String busId) {
        Bus bus = getBusById(busId);
        return (bus != null) ? bus.getTotalSeats() : -1;
    }
}
