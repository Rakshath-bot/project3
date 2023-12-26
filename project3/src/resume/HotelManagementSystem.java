package resume;

import java.util.ArrayList;
import java.util.Scanner;

class Room {
    private int roomNumber;
    private boolean isOccupied;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isOccupied = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void occupyRoom() {
        isOccupied = true;
    }

    public void vacateRoom() {
        isOccupied = false;
    }
}

class Customer {
    private String name;
    private String phoneNumber;

    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

class Booking {
    private Room room;
    private Customer customer;

    public Booking(Room room, Customer customer) {
        this.room = room;
        this.customer = customer;
        room.occupyRoom();
    }

    public Room getRoom() {
        return room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void vacateRoom() {
        room.vacateRoom();
    }
}

class Hotel {
    private ArrayList<Room> rooms;
    private ArrayList<Booking> bookings;

    public Hotel(int numRooms) {
        rooms = new ArrayList<>();
        bookings = new ArrayList<>();

        // Initialize rooms
        for (int i = 1; i <= numRooms; i++) {
            rooms.add(new Room(i));
        }
    }

    public void displayAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (!room.isOccupied()) {
                System.out.println("Room Number: " + room.getRoomNumber());
            }
        }
    }

    public Booking bookRoom(String customerName, String phoneNumber) {
        // Find the first available room
        for (Room room : rooms) {
            if (!room.isOccupied()) {
                Customer customer = new Customer(customerName, phoneNumber);
                Booking booking = new Booking(room, customer);
                bookings.add(booking);
                return booking;
            }
        }
        System.out.println("No available rooms.");
        return null;
    }

    public void displayBookings() {
        System.out.println("Current Bookings:");
        for (Booking booking : bookings) {
            System.out.println("Room Number: " + booking.getRoom().getRoomNumber() +
                    ", Customer: " + booking.getCustomer().getName() +
                    ", Phone: " + booking.getCustomer().getPhoneNumber());
        }
    }

    public void vacateRoom(int roomNumber) {
        for (Booking booking : bookings) {
            if (booking.getRoom().getRoomNumber() == roomNumber) {
                booking.vacateRoom();
                bookings.remove(booking);
                System.out.println("Room " + roomNumber + " has been vacated.");
                return;
            }
        }
        System.out.println("Room " + roomNumber + " is not booked.");
    }
}

public class HotelManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel(10); // Create a hotel with 10 rooms

        while (true) {
            System.out.println("\n1. Display Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Display Bookings");
            System.out.println("4. Vacate Room");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    hotel.displayAvailableRooms();
                    break;
                case 2:
                    System.out.print("Enter customer name: ");
                    String customerName = scanner.nextLine();
                    System.out.print("Enter customer phone number: ");
                    String phoneNumber = scanner.nextLine();
                    hotel.bookRoom(customerName, phoneNumber);
                    break;
                case 3:
                    hotel.displayBookings();
                    break;
                case 4:
                    System.out.print("Enter room number to vacate: ");
                    int roomNumber = scanner.nextInt();
                    hotel.vacateRoom(roomNumber);
                    break;
                case 5:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}



