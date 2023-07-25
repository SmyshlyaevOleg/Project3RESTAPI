package ru.smyshlyaev.springcourse.Project3RestApp.dto;

import ru.smyshlyaev.springcourse.Project3RestApp.models.Measurement;
import ru.smyshlyaev.springcourse.Project3RestApp.models.Sensor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

public class SensorDTO {

    @NotEmpty(message = "name should be not empty")
    private String sensorName;

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }




}
