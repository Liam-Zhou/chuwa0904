package Coding.CodingQuestions.coding1;

abstract class Vehicle {
    public String licensePlate;
    private VehicleSize size;

    public Vehicle (String licensePlate, VehicleSize size){
        this.licensePlate = licensePlate;
        this.size= size;
    }

    public String getLicense() {
        return licensePlate;
    }

    public VehicleSize getSize() {
        return size;
    }

}
