package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        final BusService service = new BusService();
        final Scanner scanner = new Scanner(System.in);

        // Thêm nhiều tuyến xe mẫu
        List<Bus> buses = Arrays.asList(
                new Bus("BUS1001", "Hà Nội - Sài Gòn", 10),
                new Bus("BUS1002", "Hà Nội - Đà Nẵng", 15),
                new Bus("BUS1003", "Hải Phòng - Cần Thơ", 12),
                new Bus("BUS1004", "Nha Trang - Đà Lạt", 20),
                new Bus("BUS1005", "Hồ Chí Minh - Cà Mau", 18),
                new Bus("BUS1006", "Sài Gòn - Hà Nội", 10),
                new Bus("BUS1007", "Đà Nẵng - Hà Nội", 15),
                new Bus("BUS1008", "Cần Thơ - Hải Phòng", 12),
                new Bus("BUS1009", "Đà Lạt - Nha Trang", 20),
                new Bus("BUS1010", "Cà Mau - Hồ Chí Minh", 18)
        );
        buses.forEach(service::addBus);

        while (true) {
            System.out.println("\n\uD83D\uDE8D HỆ THỐNG ĐẶT VÉ XE BUS \uD83D\uDE8D");
            System.out.println("1. Hiển thị danh sách xe");
            System.out.println("2. Đặt vé");
            System.out.println("3. Xem sơ đồ ghế");
            System.out.println("4. Tìm xe theo tuyến");
            System.out.println("5. Thoát");
            System.out.print("Chọn chức năng: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Vui lòng nhập một số hợp lệ!");
                continue;
            }

            switch (choice) {
                case 1:
                    service.displayAllBuses();
                    break;

                case 2:
                    System.out.print("Nhập ID xe: ");
                    String busId = scanner.nextLine().trim();
                    Bus selectedBus = service.getBusById(busId);
                    if (selectedBus == null) {
                        System.out.println("❌ Không tìm thấy xe với ID: " + busId);
                        continue;
                    }

                    System.out.print("Nhập tên hành khách: ");
                    String name = scanner.nextLine().trim();

                    System.out.print("Nhập số ghế: ");
                    String seatInput = scanner.nextLine().trim();
                    String[] seatNumbersStr = seatInput.split(",");
                    List<Integer> seatNumbers = new ArrayList<>();

                    for (String seatStr : seatNumbersStr) {
                        try {
                            int seatNumber = Integer.parseInt(seatStr.trim());
                            if (seatNumber < 1 || seatNumber > selectedBus.getSeats().length) {
                                System.out.println("❌ Ghế số " + seatNumber + " không hợp lệ!");
                            } else {
                                seatNumbers.add(seatNumber);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("❌ '" + seatStr + "' không phải là số ghế hợp lệ!");
                        }
                    }

                    if (seatNumbers.isEmpty()) {
                        System.out.println("❌ Không có số ghế hợp lệ để đặt!");
                        continue;
                    }

                    List<Ticket> bookedTickets = service.bookMultipleTickets(busId, name, seatNumbers);
                    if (!bookedTickets.isEmpty()) {
                        for (Ticket ticket : bookedTickets) {
                            ticket.displayTicket();
                        }
                    } else {
                        System.out.println("❌ Không đặt được vé nào, có thể tất cả ghế đã có người đặt.");
                    }
                    break;

                case 3:
                    System.out.print("Nhập ID xe: ");
                    String busIdView = scanner.nextLine().trim();
                    Bus bus = service.getBusById(busIdView);
                    if (bus != null) {
                        bus.displaySeats();
                    } else {
                        System.out.println("❌ Không tìm thấy xe!");
                    }
                    break;

                case 4:
                    System.out.print("Nhập tuyến cần tìm: ");
                    String route = scanner.nextLine().trim();
                    service.searchBusByRoute(route);
                    break;

                case 5:
                    System.out.println("Cảm ơn bạn đã sử dụng hệ thống!");
                    scanner.close();
                    return;

                default:
                    System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        }
    }
}
