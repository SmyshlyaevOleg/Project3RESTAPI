package ru.smyshlyaev.springcourse.Project3RestApp.dto;

import jdk.jfr.BooleanFlag;
import org.hibernate.validator.constraints.Range;
import ru.smyshlyaev.springcourse.Project3RestApp.models.Sensor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MeasurementDTO {

   // @NotEmpty(message = "value should be not empty")
    @Range(min = -45, max = 45, message = "value should be of -100 to 100")
    private int value;

    @BooleanFlag
    private boolean raining;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    @NotNull
    private SensorDTO sourceSensor;

    public SensorDTO getSourceSensor() {
        return sourceSensor;
    }

    public void setSourceSensor(SensorDTO sourceSensor) {
        this.sourceSensor = sourceSensor;
    }
}
