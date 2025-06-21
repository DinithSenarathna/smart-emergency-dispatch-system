package datastructures;

import model.Edge;
import java.util.*;

public class Graph {
    private Map<Integer, List<Edge>> adjList = new HashMap<>();

    public void addLocation(int location) {
        adjList.putIfAbsent(location, new ArrayList<>());
    }

    public void addRoad(int source, int destination, int distance) {
        adjList.computeIfAbsent(source, k -> new ArrayList<>()).add(new Edge(destination, distance));
        adjList.computeIfAbsent(destination, k -> new ArrayList<>()).add(new Edge(source, distance));
    }

    public Map<Integer, Integer> dijkstra(int start) {
        Map<Integer, Integer> distances = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        for (int node : adjList.keySet()) distances.put(node, Integer.MAX_VALUE);
        distances.put(start, 0);
        pq.add(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int dist = current[1];

            if (dist > distances.get(node)) continue;

            for (Edge edge : adjList.getOrDefault(node, Collections.emptyList())) {
                int newDist = dist + edge.getWeight();
                if (newDist < distances.get(edge.getDestination())) {
                    distances.put(edge.getDestination(), newDist);
                    pq.add(new int[]{edge.getDestination(), newDist});
                }
            }
        }
        return distances;
    }
}
