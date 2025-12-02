package Simulation;

import java.util.concurrent.locks.ReentrantLock;

public class HighwaySimulator {
    // Shared counter - initially unsynchronized to demonstrate race condition
    private static int highwayDistance = 0;

    // For synchronized version
    private boolean useSynchronization = false;
    private ReentrantLock lock = new ReentrantLock();

    // Reset the counter
    public void resetHighwayDistance() {
        highwayDistance = 0;
    }

    public int getHighwayDistance() {
        return highwayDistance;
    }

    // UNSYNCHRONIZED version - causes race condition
    public void incrementHighwayDistanceUnsafe(int distance) {
        // This will cause race condition with multiple threads
        int temp = highwayDistance;
        // Simulate some processing time to make race condition more visible
        try {
            Thread.sleep(0, 100); // 100 nanoseconds
        } catch (InterruptedException e) {
            // Ignore
        }
        highwayDistance = temp + distance;
    }

    // SYNCHRONIZED version using synchronized keyword
    public synchronized void incrementHighwayDistanceSynchronized(int distance) {
        highwayDistance += distance;
    }

    // SYNCHRONIZED version using ReentrantLock
    public void incrementHighwayDistanceLocked(int distance) {
        lock.lock();
        try {
            highwayDistance += distance;
        } finally {
            lock.unlock();
        }
    }

    // Main method that switches between synchronized and unsynchronized
    public void incrementHighwayDistance(int distance) {
        if (useSynchronization) {
            // Use synchronized method
            incrementHighwayDistanceSynchronized(distance);
            // OR use ReentrantLock
            // incrementHighwayDistanceLocked(distance);
        } else {
            // Use unsafe method to demonstrate race condition
            incrementHighwayDistanceUnsafe(distance);
        }
    }

    public void enableSynchronization() {
        useSynchronization = true;
    }

    public void disableSynchronization() {
        useSynchronization = false;
    }

    public boolean isSynchronizationEnabled() {
        return useSynchronization;
    }
}