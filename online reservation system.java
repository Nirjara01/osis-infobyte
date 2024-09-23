import java.util.ArrayList;
import java.util.Scanner;

// Class to store reservation details
class Reservation {
    private String name;
    private String timeSlot;

    public Reservation(String name, String timeSlot) {
        this.name = name;
        this.timeSlot = timeSlot;
    }

    public String getName() {
        return name;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    @Override
    public String toString() {
        return "Reservation [Name: " + name + ", Time Slot: " + timeSlot + "]";
    }
}

// Reservation system class
class ReservationSystem {
    private ArrayList<Reservation> reservations;
    private String[] availableSlots;

    public ReservationSystem() {
        reservations = new ArrayList<>();
        availableSlots = new String[] { "9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM" };
    }

    // Display available time slots
    public void displayAvailableSlots() {
        System.out.println("Available time slots:");
        for (int i = 0; i < availableSlots.length; i++) {
            if (!isSlotBooked(availableSlots[i])) {
                System.out.println((i + 1) + ". " + availableSlots[i]);
            }
        }
    }

    // Check if a slot is already booked
    private boolean isSlotBooked(String slot) {
        for (Reservation res : reservations) {
            if (res.getTimeSlot().equals(slot)) {
                return true;
            }
        }
        return false;
    }

    // Make a reservation
    public void makeReservation(String name, int slotChoice) {
        if (slotChoice < 1 || slotChoice > availableSlots.length) {
            System.out.println("Invalid slot choice.");
            return;
        }

        String chosenSlot = availableSlots[slotChoice - 1];
        if (isSlotBooked(chosenSlot)) {
            System.out.println("Sorry, this slot is already booked.");
        } else {
            reservations.add(new Reservation(name, chosenSlot));
            System.out.println("Reservation made successfully for " + name + " at " + chosenSlot);
        }
    }

    // Display all reservations
    public void displayReservations() {
        System.out.println("\nCurrent reservations:");
        if (reservations.isEmpty()) {
            System.out.println("No reservations made yet.");
        } else {
            for (Reservation res : reservations) {
                System.out.println(res);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReservationSystem system = new ReservationSystem();

        while (true) {
            System.out.println("\n1. Display available time slots");
            System.out.println("2. Make a reservation");
            System.out.println("3. View all reservations");
            System.out.println("4. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    system.displayAvailableSlots();
                    break;
                case 2:
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    system.displayAvailableSlots();
                    System.out.print("Choose a time slot (enter number): ");
                    int slotChoice = scanner.nextInt();
                    system.makeReservation(name, slotChoice);
                    break;
                case 3:
                    system.displayReservations();
                    break;
                case 4:
                    System.out.println("Exiting the reservation system.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
