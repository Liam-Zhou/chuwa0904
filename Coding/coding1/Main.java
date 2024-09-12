// Enums for VehicleType, ParkingSpotType, AccountStatus, and ParkingTicketStatus
enum VehicleType {
    CAR, TRUCK, ELECTRIC, VAN, MOTORBIKE
}

enum ParkingSpotType {
    HANDICAPPED, COMPACT, LARGE, MOTORBIKE, ELECTRIC
}

enum AccountStatus {
    ACTIVE, BLOCKED, BANNED, COMPROMISED, ARCHIVED, UNKNOWN
}

enum ParkingTicketStatus {
    ACTIVE, PAID, LOST
}

// Address class
class Address {
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    public Address(String street, String city, String state, String zipCode, String country) {
        this.streetAddress = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    // Getters and Setters (if needed)
}

// Person class
class Person {
    private String name;
    private Address address;
    private String email;
    private String phone;

    public Person(String name, Address address, String email, String phone) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    // Getters and Setters (if needed)
}

// Account, Admin, and ParkingAttendant classes
class Account {
    private String userName;
    private String password;
    private Person person;
    private AccountStatus status;

    public Account(String userName, String password, Person person, AccountStatus status) {
        this.userName = userName;
        this.password = password;
        this.person = person;
        this.status = status;
    }

    public void resetPassword() {
        // Implementation to reset password
    }
}

class Admin extends Account {
    public Admin(String userName, String password, Person person, AccountStatus status) {
        super(userName, password, person, status);
    }

    public void addParkingFloor(ParkingFloor floor) {
        // Implementation to add parking floor
    }

    public void addParkingSpot(String floorName, ParkingSpot spot) {
        // Implementation to add parking spot
    }

    public void addParkingDisplayBoard(String floorName, ParkingDisplayBoard displayBoard) {
        // Implementation to add parking display board
    }

    public void addCustomerInfoPanel(String floorName, InfoPanel infoPanel) {
        // Implementation to add customer info panel
    }

    public void addEntrancePanel(EntrancePanel entrancePanel) {
        // Implementation to add entrance panel
    }

    public void addExitPanel(ExitPanel exitPanel) {
        // Implementation to add exit panel
    }
}

class ParkingAttendant extends Account {
    public ParkingAttendant(String userName, String password, Person person, AccountStatus status) {
        super(userName, password, person, status);
    }

    public void processTicket(String ticketNumber) {
        // Implementation to process ticket
    }
}

// ParkingSpot and its children classes
abstract class ParkingSpot {
    private String number;
    private boolean isFree;
    private Vehicle vehicle;
    private ParkingSpotType parkingSpotType;

    public ParkingSpot(String number, ParkingSpotType type) {
        this.number = number;
        this.isFree = true;
        this.vehicle = null;
        this.parkingSpotType = type;
    }

    public boolean isFree() {
        return isFree;
    }

    public void assignVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isFree = false;
    }

    public void removeVehicle() {
        this.vehicle = null;
        this.isFree = true;
    }

    // Getters for number and type (if needed)
}

class HandicappedSpot extends ParkingSpot {
    public HandicappedSpot(String number) {
        super(number, ParkingSpotType.HANDICAPPED);
    }
}

class CompactSpot extends ParkingSpot {
    public CompactSpot(String number) {
        super(number, ParkingSpotType.COMPACT);
    }
}

class LargeSpot extends ParkingSpot {
    public LargeSpot(String number) {
        super(number, ParkingSpotType.LARGE);
    }
}

class MotorbikeSpot extends ParkingSpot {
    public MotorbikeSpot(String number) {
        super(number, ParkingSpotType.MOTORBIKE);
    }
}

class ElectricSpot extends ParkingSpot {
    public ElectricSpot(String number) {
        super(number, ParkingSpotType.ELECTRIC);
    }
}

// ParkingFloor class
class ParkingFloor {
    private String name;
    private Map<String, ParkingSpot> handicappedSpots = new HashMap<>();
    private Map<String, ParkingSpot> compactSpots = new HashMap<>();
    private Map<String, ParkingSpot> largeSpots = new HashMap<>();
    private Map<String, ParkingSpot> motorbikeSpots = new HashMap<>();
    private Map<String, ParkingSpot> electricSpots = new HashMap<>();
    private ParkingDisplayBoard displayBoard;

    public ParkingFloor(String name) {
        this.name = name;
        this.displayBoard = new ParkingDisplayBoard();
    }

    public void addParkingSpot(ParkingSpot spot) {
        switch (spot.getParkingSpotType()) {
            case HANDICAPPED -> handicappedSpots.put(spot.getNumber(), spot);
            case COMPACT -> compactSpots.put(spot.getNumber(), spot);
            case LARGE -> largeSpots.put(spot.getNumber(), spot);
            case MOTORBIKE -> motorbikeSpots.put(spot.getNumber(), spot);
            case ELECTRIC -> electricSpots.put(spot.getNumber(), spot);
            default -> System.out.println("Wrong parking spot type");
        }
    }

    public void assignVehicleToSpot(Vehicle vehicle, ParkingSpot spot) {
        spot.assignVehicle(vehicle);
        updateDisplayBoard(spot);
    }

    private void updateDisplayBoard(ParkingSpot spot) {
        // Update display logic for each spot type
    }

    public void freeSpot(ParkingSpot spot) {
        spot.removeVehicle();
        // Update free spot count logic
    }
}

// ParkingDisplayBoard class
class ParkingDisplayBoard {
    private String id;
    private ParkingSpot handicappedFreeSpot;
    private ParkingSpot compactFreeSpot;
    private ParkingSpot largeFreeSpot;
    private ParkingSpot motorbikeFreeSpot;
    private ParkingSpot electricFreeSpot;

    public ParkingDisplayBoard() {
        // Initialize board spots
    }

    public void showEmptySpotNumber() {
        StringBuilder message = new StringBuilder();
        message.append(handicappedFreeSpot.isFree() ? "Free Handicapped: " + handicappedFreeSpot.getNumber() : "Handicapped is full").append("\n");
        message.append(compactFreeSpot.isFree() ? "Free Compact: " + compactFreeSpot.getNumber() : "Compact is full").append("\n");
        message.append(largeFreeSpot.isFree() ? "Free Large: " + largeFreeSpot.getNumber() : "Large is full").append("\n");
        message.append(motorbikeFreeSpot.isFree() ? "Free Motorbike: " + motorbikeFreeSpot.getNumber() : "Motorbike is full").append("\n");
        message.append(electricFreeSpot.isFree() ? "Free Electric: " + electricFreeSpot.getNumber() : "Electric is full");
        System.out.println(message.toString());
    }

    // Getters and Setters (if needed)
}

// ParkingLot class
class ParkingLot {
    private static ParkingLot instance;
    private String name;
    private Address address;
    private Map<String, ParkingFloor> parkingFloors = new HashMap<>();
    private ReentrantLock lock = new ReentrantLock();

    private ParkingLot(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public static synchronized ParkingLot getInstance(String name, Address address) {
        if (instance == null) {
            instance = new ParkingLot(name, address);
        }
        return instance;
    }

    public ParkingTicket getNewParkingTicket(Vehicle vehicle) throws Exception {
        if (isFull(vehicle.getVehicleType())) {
            throw new Exception("Parking full!");
        }
        lock.lock();
        try {
            ParkingTicket ticket = new ParkingTicket();
            vehicle.assignTicket(ticket);
            ticket.saveInDB();
            incrementSpotCount(vehicle.getVehicleType());
            // Save ticket to active tickets
            return ticket;
        } finally {
            lock.unlock();
        }
    }

    private boolean isFull(VehicleType type) {
        // Check if parking is full based on vehicle type
        return false;
    }

    private void incrementSpotCount(VehicleType type) {
        // Increment spot count logic based on vehicle type
    }

    // Other methods to add parking floors, entrance, and exit panels
}