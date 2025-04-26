import java.util.ArrayList;
import java.util.List;

public class AirportTowerSimulatorDemo {
    public static void main(String[] args) {
        ControlTower tower = new ControlTower();
        List<Aircraft> aircrafts = new ArrayList<>();

        Aircraft a1 = new PassengerPlane("Passenger-1", 5);
        Aircraft a2 = new CargoPlane("Cargo-1", 6);
        Aircraft a3 = new Helicopter("Helicopter-1", 3);
        Aircraft a4 = new PassengerPlane("Passenger-2", 8);
        Aircraft a5 = new CargoPlane("Cargo-2", 2);

        aircrafts.add(a1);
        aircrafts.add(a2);
        aircrafts.add(a3);
        aircrafts.add(a4);
        aircrafts.add(a5);

        tower.requestLanding(a1);
        tower.requestTakeoff(a2);
        tower.requestLanding(a3);

        processTowerRequests(tower, aircrafts);

        System.out.println("\n--- Emergency situation incoming ---\n");

        a5.send("MAYDAY", tower);
        tower.requestLanding(a5);

        processTowerRequests(tower, aircrafts);

        System.out.println("\n--- Simulation finished ---");
    }

    private static void processTowerRequests(ControlTower tower, List<Aircraft> aircrafts) {
        for (Aircraft a : aircrafts) {
            if (tower.requestRunway(a)) {
                System.out.println("[Tower]: " + a.getId() + " used the runway.");
                tower.runwayVacated();
            }
        }
    }
}
