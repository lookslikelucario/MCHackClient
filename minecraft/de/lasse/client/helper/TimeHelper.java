package de.lasse.client.helper;

public class TimeHelper {

    private long lastMS;

    private long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }

    public boolean hasReached(long ms) {
        return getCurrentMS() - lastMS >= ms;
    }

    public void reset() {
        lastMS = getCurrentMS();
    }
}
