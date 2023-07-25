package ru.smyshlyaev.springcourse.Project3RestApp.models;

import jdk.jfr.BooleanFlag;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Component
@Table(name = "measurements")
public class Measurement { // класс для измерений

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int measurementId;

  //  @NotEmpty(message = "value should be not empty")
    @Column(name = "value")
    @Range(min = -45, max = 45, message = "value should be of -100 to 100")
    private double value;

    @Column(name = "raining")
    @BooleanFlag
    private boolean raining;

    @DateTimeFormat
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "sensor",referencedColumnName = "sensor_name")
    private Sensor sourceSensor;

    public Measurement() {

    }

    public Measurement(int value, boolean raining) {
        this.value = value;
        this.raining = raining;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Sensor getSourceSensor() {
        return sourceSensor;
    }

    public void setSourceSensor(Sensor sourceSensor) {
        this.sourceSensor = sourceSensor;
    }
}
