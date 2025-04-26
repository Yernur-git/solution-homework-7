import java.util.*;

public class ControlTower implements TowerMediator {
    private List<Aircraft> landingQueue = new ArrayList<>();
    private List<Aircraft> takeoffQueue = new ArrayList<>();
    private boolean runwayAvailable = true;

    public synchronized void broadcast(String msg, Aircraft sender) {
        System.out.println("[Tower]: Received '" + msg + "' from " + sender.getId());

        if (msg.equalsIgnoreCase("MAYDAY")) {
            System.out.println("[Tower]: Emergency detected! Clearing runway immediately for " + sender.getId());
            landingQueue.add(0, sender);
            notifyAllAircraftHold(sender);
        }
    }

    private void notifyAllAircraftHold(Aircraft except) {
        for (Aircraft a : landingQueue) {
            if (!a.equals(except)) {
                a.receive("Hold your position, emergency in progress!");
            }
        }
        for (Aircraft a : takeoffQueue) {
            if (!a.equals(except)) {
                a.receive("Hold your position, emergency in progress!");
            }
        }
    }

    @Override
    public synchronized boolean requestRunway(Aircraft a) {
        if (!runwayAvailable) {
            return false;
        }
        if (!landingQueue.isEmpty() && landingQueue.get(0).equals(a)) {
            landingQueue.remove(0);
            runwayAvailable = false;
            System.out.println("[Tower]: " + a.getId() + " cleared to land!");
            return true;
        } else if (!takeoffQueue.isEmpty() && takeoffQueue.get(0).equals(a)) {
            takeoffQueue.remove(0);
            runwayAvailable = false;
            System.out.println("[Tower]: " + a.getId() + " cleared for take-off!");
            return true;
        }
        return false;
    }

    public synchronized void requestLanding(Aircraft a) {
        if (a.getFuelLevel() <= 2) {
            System.out.println("[Tower]: " + a.getId() + " low fuel detected! Priority landing requested.");
            landingQueue.add(0, a);
        } else {
            landingQueue.add(a);
        }
    }

    public synchronized void requestTakeoff(Aircraft a) {
        takeoffQueue.add(a);
    }

    public synchronized void runwayVacated() {
        runwayAvailable = true;
    }
}
