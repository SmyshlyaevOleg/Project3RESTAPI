package ru.smyshlyaev.springcourse.Project3RestApp.util.measurementException;

public class MeasurementNotCreatedException extends RuntimeException{
    public  MeasurementNotCreatedException(String mgs) {
        super(mgs);
    }
}
