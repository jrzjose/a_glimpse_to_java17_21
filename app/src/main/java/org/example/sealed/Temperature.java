package org.example.sealed;

public non-sealed class Temperature extends Sensor {

    public Temperature(int low, int high, int reading) {
        super(low, high, reading);
    }

    @Override
    public int calibration() {
        return reading * 1; // should be a formula for converting raw signal 
    }
}
