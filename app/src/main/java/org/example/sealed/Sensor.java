package org.example.sealed;

public sealed abstract class Sensor permits Temperature, Humidity, Presure {
    protected int low;
    protected int high;
    protected int reading;
    
    public Sensor(int low, int high, int reading) {
        this.low = low;
        this.high = high;
        this.reading = reading;
    }

    public short status() {
        if (reading <= low) {
            return -1;
        }
        else if (reading >= high) {
            return 1;
        }

        return 0;
    }

    public void alert() {
        if (reading <= low) {
            System.out.println("Running low!");
        }
        else if (reading >= high) {
            System.out.println("Running h... above normal!");
        }
    }

    public abstract int calibration();
}
