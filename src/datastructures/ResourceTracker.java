package datastructures;

import model.Station;
import model.EmergencyType;

import java.util.*;

public class ResourceTracker {
    private Map<EmergencyType, List<Station>> stations = new EnumMap<>(EmergencyType.class);

    public void addStation(Station station) {
        stations.computeIfAbsent(station.getType(), k -> new ArrayList<>()).add(station);
    }

    public Station findClosestAvailable(EmergencyType type, int location, Map<Integer, Integer> distances) {
        Station closest = null;
        int minDistance = Integer.MAX_VALUE;

        for (Station station : stations.getOrDefault(type, Collections.emptyList())) {
            if (station.getAvailableUnits() > 0) {
                int dist = distances.getOrDefault(station.getLocation(), Integer.MAX_VALUE);
                if (dist < minDistance) {
                    minDistance = dist;
                    closest = station;
                }
            }
        }
        return closest;
    }
}
