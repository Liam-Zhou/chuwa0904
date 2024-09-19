package coding1.src.test.java;

import coding1.src.main.java.org.example.ParkingLot;
import coding1.src.main.java.org.example.Vehicle;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParkingLotTest {

    @Test
    public void testParkingLot() {
        ParkingLot parkingLot = new ParkingLot(2);
        Vehicle veh1 = new Vehicle("HEY123");
        Vehicle veh2 = new Vehicle("WED999");

        assertTrue(parkingLot.canParkVehicle(veh1));
        assertTrue(parkingLot.canParkVehicle(veh2));
        assertFalse(parkingLot.canParkVehicle(new Vehicle("XYZ000")));

        assertTrue(parkingLot.canRemoveVehicle(veh1));
        assertTrue(parkingLot.canRemoveVehicle(veh2));
        assertFalse(parkingLot.canRemoveVehicle(veh2));

    }
}
