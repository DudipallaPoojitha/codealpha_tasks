
    public class Booking {
    private String guestName;
    private Room room;
    private int nights;
    private double totalBill;

    public Booking(String guestName, Room room, int nights) {
        this.guestName = guestName;
        this.room = room;
        this.nights = nights;
        this.totalBill = room.getPricePerNight() * nights;
    }

    public void printBookingDetails() {
        System.out.println("\n------- BOOKING CONFIRMATION -------");
        System.out.println("Guest Name     : " + guestName);
        System.out.println("Room Details   : Room #" + room.getRoomNumber() + " (" + room.getCategory() + ")");
        System.out.println("Duration       : " + nights + " night(s)");
        System.out.printf("Total Amount   : $%.2f\n", totalBill);
        System.out.println("Status         : Paid & Confirmed");
        System.out.println("------------------------------------");
    }
}

