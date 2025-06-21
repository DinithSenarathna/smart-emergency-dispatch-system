import model.*;
import datastructures.*;

import java.util.*;

public class EmergencyDispatchSystem {
    private final EmergencyHeap emergencyHeap = new EmergencyHeap();
    private final ResourceTracker resourceTracker = new ResourceTracker();
    private final Graph cityMap = new Graph();
    private final Scanner scanner = new Scanner(System.in);

    public void startSimulation() {
        System.out.println("🚨 Emergency Dispatch System Simulation 🚨");

        while (true) {
            System.out.println("\n1. Add Emergency\n2. Add Station\n3. Add Road\n4. Run Dispatch\n5. Exit");
            System.out.print("Select option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: addEmergency(); break;
                case 2: addStation(); break;
                case 3: addRoad(); break;
                case 4: runDispatch(); break;
                case 5: System.out.println("Exiting simulation."); return;
                default: System.out.println("Invalid option");
            }
        }
    }

    private void addEmergency() {
        System.out.print("Emergency type (FIRE, MEDICAL, THEFT): ");
        EmergencyType type = EmergencyType.valueOf(scanner.next().toUpperCase());
        System.out.print("Location (node ID): ");
        int location = scanner.nextInt();
        System.out.print("Severity (1-10): ");
        int severity = scanner.nextInt();
        System.out.print("Report time (timestamp): ");
        long time = scanner.nextLong();

        emergencyHeap.addEmergency(new Emergency(type, location, severity, time));
        System.out.println("✅ Emergency added!");
    }

    private void addStation() {
        System.out.print("Station type (FIRE, MEDICAL, THEFT): ");
        EmergencyType type = EmergencyType.valueOf(scanner.next().toUpperCase());
        System.out.print("Location (node ID): ");
        int location = scanner.nextInt();
        System.out.print("Available units: ");
        int units = scanner.nextInt();

        resourceTracker.addStation(new Station(type, location, units));
        cityMap.addLocation(location);
        System.out.println("🏢 Station added!");
    }

    private void addRoad() {
        System.out.print("Source location: ");
        int source = scanner.nextInt();
        System.out.print("Destination location: ");
        int dest = scanner.nextInt();
        System.out.print("Distance: ");
        int distance = scanner.nextInt();

        cityMap.addRoad(source, dest, distance);
        System.out.println("🛣️ Road added!");
    }

    private void runDispatch() {
        System.out.println("\n=== DISPATCHING EMERGENCIES ===");
        while (!emergencyHeap.isEmpty()) {
            Emergency emergency = emergencyHeap.getNextEmergency();
            Map<Integer, Integer> distances = cityMap.dijkstra(emergency.getLocation());
            Station closest = resourceTracker.findClosestAvailable(
                    emergency.getType(),
                    emergency.getLocation(),
                    distances
            );

            if (closest != null) {
                closest.assignUnit();
                int dist = distances.get(closest.getLocation());
                System.out.printf(
                        "🔥 [%s] Location: %d | Assigned station: %d | Distance: %d | Severity: %d%n",
                        emergency.getType(),
                        emergency.getLocation(),
                        closest.getLocation(),
                        dist,
                        emergency.getSeverity()
                );
            } else {
                System.out.printf(
                        "❌ [%s] Location: %d | NO STATION AVAILABLE | Severity: %d%n",
                        emergency.getType(),
                        emergency.getLocation(),
                        emergency.getSeverity()
                );
            }
        }
        System.out.println("=== DISPATCH COMPLETE ===");
    }
}
