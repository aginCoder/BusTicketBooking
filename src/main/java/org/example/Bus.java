package org.example;

public class Bus {
    private String id;
    private String route;
    private int totalSeats;
    private boolean[] seats; // Mảng ghế, true = đã đặt, false = còn trống

    public Bus(String id, String route, int totalSeats) {
        this.id = id;
        this.route = route;
        this.totalSeats = totalSeats;
        this.seats = new boolean[totalSeats]; // Mặc định tất cả đều trống
    }

    public boolean bookSeat(int seatNumber) {
        if (seatNumber < 1 || seatNumber > totalSeats) { // Kiểm tra số ghế hợp lệ (1-based)
            return false;
        }
        if (seats[seatNumber - 1]) { // Kiểm tra ghế đã được đặt chưa
            return false;
        }
        seats[seatNumber - 1] = true; // Đánh dấu ghế đã đặt
        return true;
    }


    public void displaySeats() {
        System.out.println("Sơ đồ chỗ ngồi:");
        for (int i = 0; i < totalSeats; i++) {
            System.out.print(seats[i] ? " [X] " : " [O] ");
        }
        System.out.println();
    }

    public String getId() {
        return id;
    }

    public String getRoute() {
        return route;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    // phương thức getSeats() để sửa lỗi
    public boolean[] getSeats() {
        return seats;
    }
}
