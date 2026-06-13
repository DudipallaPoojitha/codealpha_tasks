import java.util.ArrayList;
import java.util.Scanner;

public class HotelSystem {
    private static ArrayList<Room> hotelRooms = new ArrayList<>();
    private static ArrayList<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {
        initializeHotelRooms();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to the CodeAlpha Grand Hotel!");
        
        while (true) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left behind
            
            if (choice == 1) {
                displayAvailableRooms();
            } else if (choice == 2) {
                processBooking(scanner);
            } else if (choice == 3) {
                System.out.println("Thank you for using our system. Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }

    private static void initializeHotelRooms() {
        // Populating the hotel with a few rooms across categories
        hotelRooms.add(new Room(101, "Standard", 80.0));
        hotelRooms.add(new Room(102, "Standard", 80.0));
        hotelRooms.add(new Room(201, "Deluxe", 150.0));
        hotelRooms.add(new Room(202, "Deluxe", 150.0));
        hotelRooms.add(new Room(301, "Suite", 300.0));
    }

    private static void displayAvailableRooms() {
        System.out.println("\n--- Available Rooms ---");
        boolean found = false;
        for (Room room : hotelRooms) {
            if (room.isAvailable()) {
                System.out.println(room);
                found = true;
            }
        }
        if (!found) System.out.println("Sorry, no rooms are currently vacant.");
    }

    private static void processBooking(Scanner scanner) {
        System.out.print("\nEnter your full name: ");
        String guestName = scanner.nextLine();
        
        displayAvailableRooms();
        System.out.print("\nEnter the room number you wish to reserve: ");
        int selectedRoomNum = scanner.nextInt();
        System.out.print("How many nights are you staying?: ");
        int nights = scanner.nextInt();
        
        Room selectedRoom = null;
        for (Room room : hotelRooms) {
            if (room.getRoomNumber() == selectedRoomNum && room.isAvailable()) {
                selectedRoom = room;
                break;
            }
        }
        
        if (selectedRoom != null) {
            // Simulate Payment Processing
            System.out.printf("Processing payment of $%.2f...\n", (selectedRoom.getPricePerNight() * nights));
            System.out.println("Payment Successful!");
            
            // Finalize Booking
            selectedRoom.setAvailable(false);
            Booking reservation = new Booking(guestName, selectedRoom, nights);
            bookings.add(reservation);
            
            reservation.printBookingDetails();
        } else {
            System.out.println("Error: Room is either invalid or already taken.");
        }
    }
}

