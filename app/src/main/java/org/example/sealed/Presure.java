package org.example.sealed;

public final class Presure extends Sensor {
    
    public Presure(int low, int high, int reading) {
        super(low, high, reading);
    }

    @Override
    public int calibration() {
        return reading * 1; // should be a formula for converting raw signal 
    }
}
