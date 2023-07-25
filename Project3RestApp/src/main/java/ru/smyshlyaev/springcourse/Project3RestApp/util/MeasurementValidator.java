package ru.smyshlyaev.springcourse.Project3RestApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.smyshlyaev.springcourse.Project3RestApp.models.Measurement;
import ru.smyshlyaev.springcourse.Project3RestApp.services.SensorService;

@Component
public class MeasurementValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Measurement.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Measurement measurement = (Measurement) o;

        if (measurement.getSourceSensor() == null) {
            return;
        }

        if (sensorService.findBySensorName(measurement.getSourceSensor().getSensorName()).isEmpty())
            errors.rejectValue("sourceSensor","" ,"Нет зарегистрированного сенсора с таким именем!");
    }
}
