public class Main {
    public static void main(String[] args) {

        ParkingSystem obj = new ParkingSystem(2, 5, 5);
        boolean param_1 = obj.addCar(1);
        boolean param_2 = obj.addCar(1);
        boolean param_3 = obj.addCar(1);
        boolean param_4 = obj.addCar(2);
        boolean param_5 = obj.addCar(2);
        boolean param_6 = obj.addCar(2);
        boolean param_7 = obj.addCar(2);
        boolean param_8 = obj.addCar(2);
        boolean param_9 = obj.addCar(2);
        boolean param_10 = obj.addCar(3);
        boolean param_11 = obj.addCar(3);
        boolean param_12 = obj.addCar(3);
        boolean param_13 = obj.addCar(3);
        boolean param_14 = obj.addCar(3);
        boolean param_15 = obj.addCar(3);
        boolean param_16 = obj.addCar(4);

        boolean pparam_1 = obj.removeCar(1);
        boolean pparam_2 = obj.removeCar(1);
        boolean pparam_3 = obj.removeCar(1);
        boolean pparam_4 = obj.removeCar(2);
        boolean pparam_5 = obj.removeCar(2);
        boolean pparam_6 = obj.removeCar(2);
        boolean pparam_7 = obj.removeCar(2);
        boolean pparam_8 = obj.removeCar(2);
        boolean pparam_9 = obj.removeCar(2);
        boolean pparam_10 = obj.removeCar(3);
        boolean pparam_11 = obj.removeCar(3);
        boolean pparam_12 = obj.removeCar(3);
        boolean pparam_13 = obj.removeCar(3);
        boolean pparam_14 = obj.removeCar(3);
        boolean pparam_15 = obj.removeCar(3);
        boolean pparam_16 = obj.removeCar(4);
    }
}

class ParkingSystem {
    private int bigSlots;
    private int mediumSlots;
    private int smallSlots;
    private int maxBigSlots;
    private int maxMediumSlots;
    private int maxSmallSlots;
    public ParkingSystem(int big, int medium, int small) {
        bigSlots = big;
        mediumSlots = medium;
        smallSlots = small;
        maxBigSlots = big;
        maxMediumSlots = medium;
        maxSmallSlots = small;
    }

    public boolean addCar(int carType) {
        if (carType == 1 && bigSlots > 0) {
            bigSlots--;
            System.out.println("Big car in parking lot");
            return true;
        } else if (carType == 2 && mediumSlots > 0) {
            mediumSlots--;
            System.out.println("Medium car in parking lot");
            return true;
        } else if (carType == 3 && smallSlots > 0) {
            smallSlots--;
            System.out.println("Small car in parking lot");
            return true;
        } else if (carType != 1 && carType != 2 && carType != 3) {
            System.out.println("You can not park here.");
            return false;
        } else {
            System.out.println("No available parking slot for your car.");
            return false;
        }
    }

    public boolean removeCar(int carType) {
        if (carType == 1 && bigSlots < maxBigSlots ) {
            bigSlots++;
            System.out.println("Bye big car, c u next time");
            return true;
        } else if (carType == 2 && mediumSlots < maxMediumSlots) {
            mediumSlots++;
            System.out.println("Bye medium car, c u next time");
            return true;
        } else if (carType == 3 && smallSlots < maxSmallSlots) {
            smallSlots++;
            System.out.println("Bye small car, c u next time");
            return true;
        } else if (carType != 1 && carType != 2 && carType != 3) {
            System.out.println("we don't have this car");
            return false;
        } else {
            System.out.println("we don't have your car");
            return false;
        }
    }
}
