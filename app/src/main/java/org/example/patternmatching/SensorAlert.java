package org.example.patternmatching;

import org.example.sealed.Humidity;
import org.example.sealed.Presure;
import org.example.sealed.Sensor;
import org.example.sealed.Temperature;

public class SensorAlert {
    public void checkUsingInstanceOf(Sensor sensor) {
        if (sensor.status() == 0)
            return;

        // Pattern matching for `instance of`
        // before Java21 `Temperature temp = (Temperature) sensor;
        // ~
        if (sensor instanceof Temperature temp) {
            temp.alert();
        } else if (sensor instanceof Humidity humidity) {
            humidity.alert();
        } else if (sensor instanceof Presure presure) {
            presure.alert();
        }
    }

    public void checkUsingSwitch(Sensor sensor) {
        if (sensor.status() == 0)
            return;

        // Pattern matching for `switch`
        switch (sensor) {
            case Temperature temp -> temp.alert();
            case Humidity humidity -> humidity.alert();
            case Presure presure when presure.status() == -1 -> presure.alert();
            default -> System.out.println("no alert - exhausted");
        }
    }
}
