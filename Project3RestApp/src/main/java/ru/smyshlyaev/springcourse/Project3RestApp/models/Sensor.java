package ru.smyshlyaev.springcourse.Project3RestApp.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sensors")
public class Sensor implements Serializable { // класс сенсора

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int sensorId;

    @NotEmpty(message = "name should be not empty")
    @Size(min = 3, max = 30, message = "name should be of 3 to 30 symbols")
    @Column(name = "sensor_name")
    private String sensorName;

    @OneToMany(mappedBy = "sourceSensor")
    List <Measurement> measurementValues;

    public Sensor() {

    }

    public Sensor(String sensorName, LocalDateTime createdAt, List<Measurement> measurementValues) {
        this.sensorName = sensorName;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setName(String name) {
        this.sensorName = name;
    }

}
