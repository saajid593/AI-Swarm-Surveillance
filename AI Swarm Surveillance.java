import java.util.*;

class Drone {
    private final String id;
    private String location;
    private boolean isAnomalyDetected;
    private boolean isActive;

    public Drone(String id, String location) {
        this.id = id;
        this.location = location;
        this.isAnomalyDetected = false;
        this.isActive = true;
    }

    public void monitorArea() {
        System.out.println("[INFO] Drone " + id + " monitoring at " + location);
        detectAnomaly();
    }

    private void detectAnomaly() {
        Random random = new Random();
        isAnomalyDetected = random.nextInt(10) < 2; // 20% chance

        if (isAnomalyDetected) {
            System.out.println("[ALERT] Drone " + id + " detected anomaly at " + location);
        }
    }

    public void moveTo(String newLocation) {
        System.out.println("[ACTION] Drone " + id + " moving from " + location + " to " + newLocation);
        location = newLocation;
        isAnomalyDetected = false;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public boolean hasDetectedAnomaly() {
        return isAnomalyDetected;
    }

    public boolean isActive() {
        return isActive;
    }
}

class SwarmSystem {
    private final List<Drone> droneFleet;

    public SwarmSystem() {
        droneFleet = new ArrayList<>();
    }

    public void addDrone(Drone drone) {
        droneFleet.add(drone);
    }

    public void monitorAll() {
        for (Drone drone : droneFleet) {
            if (drone.isActive()) {
                drone.monitorArea();
            }
        }
    }

    public void handleAnomalies(String responseZone) {
        for (Drone drone : droneFleet) {
            if (!drone.hasDetectedAnomaly()) {
                drone.moveTo(responseZone);
            }
        }
    }

    public void displayDroneStatus() {
        System.out.println("\n[STATUS] Drone Positions and Anomaly Report:");
        for (Drone drone : droneFleet) {
            System.out.println("- " + drone.getId() + ": " + drone.getLocation() +
                    " | Anomaly: " + (drone.hasDetectedAnomaly() ? "Yes" : "No"));
        }
    }
}

public class AISwarmSurveillance {
    public static void main(String[] args) {
        SwarmSystem swarm = new SwarmSystem();

        // Initializing drones
        swarm.addDrone(new Drone("Drone-A", "Zone-1"));
        swarm.addDrone(new Drone("Drone-B", "Zone-2"));
        swarm.addDrone(new Drone("Drone-C", "Zone-3"));

        System.out.println("===== AI Swarm Surveillance Drone Simulation Started =====");

        // Monitoring Phase
        swarm.monitorAll();

        // Status report
        swarm.displayDroneStatus();

        // Responding to anomalies
        swarm.handleAnomalies("Incident Zone-X");

        // Final Status
        swarm.displayDroneStatus();

        System.out.println("===== Simulation Complete =====");
    }
}
