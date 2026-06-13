public class Room {
    private int roomNumber;
    private String category;
    private double pricePerNight;
    private boolean isAvailable;

    public Room(int roomNumber, String category, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true; // Rooms are available by default
    }

    // Getters and Setters
    public int getRoomNumber() { return roomNumber; }
    public String getCategory() { return category; }
    public double getPricePerNight() { return pricePerNight; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    @Override
    public String toString() {
        return "Room #" + roomNumber + " [" + category + "] - $" + pricePerNight + "/night (" + (isAvailable ? "Available" : "Booked") + ")";
    }
}