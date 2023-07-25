package ru.smyshlyaev.springcourse.Project3RestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import ru.smyshlyaev.springcourse.Project3RestApp.models.Measurement;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
}
