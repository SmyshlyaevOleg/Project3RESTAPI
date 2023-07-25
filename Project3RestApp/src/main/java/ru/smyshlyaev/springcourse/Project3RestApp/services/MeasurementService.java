package ru.smyshlyaev.springcourse.Project3RestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.smyshlyaev.springcourse.Project3RestApp.models.Measurement;
import ru.smyshlyaev.springcourse.Project3RestApp.repositories.MeasurementRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private  final SensorService sensorService;

@Autowired
    public MeasurementService(MeasurementRepository measurementRepository, Measurement measurement, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
    this.sensorService = sensorService;
}
@Transactional(readOnly = true)      // для поиска всех измерений
    public List<Measurement> findAll() {
         return measurementRepository.findAll();
    }


@Transactional                                   // для сохранения измерения и добавления времени создания измерения
    public void saveMeasurement(Measurement saveMeasurement) {
    enrichMeasurement(saveMeasurement);
    measurementRepository.save(saveMeasurement);

    }

@Transactional (readOnly = true)
    public Integer findRaining() {
    List<Measurement> measurements=measurementRepository.findAll();
    int count=0; // счетчик дождливых дней

    for (Measurement measurement: measurements)
        if (measurement.isRaining()) {
            count++;
        }
    return count;
    }

    private void enrichMeasurement(Measurement measurement) { // метод для добавления времени создания измерения
        measurement.setCreatedAt(LocalDateTime.now());
        measurement.setSourceSensor(sensorService.findBySensorName(measurement.getSourceSensor().getSensorName()).get());
        //person.setCreatedWho("ADMIN");
    }
}
