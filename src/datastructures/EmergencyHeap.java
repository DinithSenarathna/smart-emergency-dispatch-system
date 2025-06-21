package datastructures;

import model.Emergency;
import java.util.*;

public class EmergencyHeap {
    private List<Emergency> heap = new ArrayList<>();

    public void addEmergency(Emergency emergency) {
        heap.add(emergency);
        heapifyUp(heap.size() - 1);
    }

    public Emergency getNextEmergency() {
        if (heap.isEmpty()) return null;
        Emergency next = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        heapifyDown(0);
        return next;
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (compare(heap.get(index), heap.get(parent)) > 0) {
                Collections.swap(heap, index, parent);
                index = parent;
            } else break;
        }
    }

    private void heapifyDown(int index) {
        int size = heap.size();
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int largest = index;

            if (left < size && compare(heap.get(left), heap.get(largest)) > 0)
                largest = left;
            if (right < size && compare(heap.get(right), heap.get(largest)) > 0)
                largest = right;

            if (largest != index) {
                Collections.swap(heap, index, largest);
                index = largest;
            } else break;
        }
    }

    private int compare(Emergency e1, Emergency e2) {
        if (e1.getSeverity() != e2.getSeverity())
            return Integer.compare(e1.getSeverity(), e2.getSeverity());
        return Long.compare(e2.getReportTime(), e1.getReportTime());
    }

    public boolean isEmpty() { return heap.isEmpty(); }
}
