package ru.smyshlyaev.springcourse.Project3RestApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.smyshlyaev.springcourse.Project3RestApp.dto.SensorDTO;
import ru.smyshlyaev.springcourse.Project3RestApp.models.Sensor;
import ru.smyshlyaev.springcourse.Project3RestApp.services.SensorService;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

@Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz); // валидатор только для сенсора(на другом классе не позволяет его вызвать)
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor= (Sensor) target;

        if(sensorService.findBySensorName(sensor.getSensorName()).isPresent()) { // если имеется сенсор с таким именем в БД
            errors.rejectValue("sensorName", "","данный сенсор имеется в базе данных");
        }

    }
}
