package ru.smyshlyaev.springcourse.Project3RestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.smyshlyaev.springcourse.Project3RestApp.models.Sensor;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor,String> {

     Optional<Sensor> findBySensorName(String name);
}
