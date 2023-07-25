package ru.smyshlyaev.springcourse.Project3RestApp.util.sensorException;

public class SensorNotCreatedException extends RuntimeException{
    public SensorNotCreatedException(String mgs) {
        super(mgs);
    }
}
