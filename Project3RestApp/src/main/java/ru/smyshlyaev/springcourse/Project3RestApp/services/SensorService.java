package ru.smyshlyaev.springcourse.Project3RestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.smyshlyaev.springcourse.Project3RestApp.models.Sensor;
import ru.smyshlyaev.springcourse.Project3RestApp.repositories.SensorRepository;
import ru.smyshlyaev.springcourse.Project3RestApp.util.sensorException.SensorNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SensorService {
    private final SensorRepository sensorRepository;

@Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Sensor> findBySensorName(String name) {
        return sensorRepository.findBySensorName(name);
    }

    @Transactional
        public void saveSensor(Sensor saveSensor) { // метод сохранения нового сенсора
            sensorRepository.save(saveSensor);
        }

}



