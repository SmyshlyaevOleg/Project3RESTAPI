package ru.smyshlyaev.springcourse.Project3RestApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.smyshlyaev.springcourse.Project3RestApp.dto.SensorDTO;
import ru.smyshlyaev.springcourse.Project3RestApp.models.Sensor;
import ru.smyshlyaev.springcourse.Project3RestApp.services.SensorService;
import ru.smyshlyaev.springcourse.Project3RestApp.util.DataErrorResponse;
import ru.smyshlyaev.springcourse.Project3RestApp.util.SensorValidator;
import ru.smyshlyaev.springcourse.Project3RestApp.util.sensorException.SensorNotCreatedException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sensor")
public class SensorController {
    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;

@Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
}

@PostMapping("/registration")// метод довавления сенсора
    public ResponseEntity<HttpStatus> newSensor(@RequestBody @Valid SensorDTO sensorDTO,
                                                     BindingResult bindingResult) {

    Sensor registrationSensor=convetrToSensor(sensorDTO);
         sensorValidator.validate(registrationSensor, bindingResult);

        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> fieldErrors=bindingResult.getFieldErrors();

        for (FieldError error: fieldErrors ){
                errorMessage.append(error.getField())
                        .append("-").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new SensorNotCreatedException(errorMessage.toString());
        }
        sensorService.saveSensor(registrationSensor);
            return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler //
    private ResponseEntity<DataErrorResponse> handleException(SensorNotCreatedException e) {
        DataErrorResponse response = new DataErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Sensor convetrToSensor(SensorDTO sensorDTO) { //метод преобразования DTO в объект этого DTO
        Sensor sensor=modelMapper.map(sensorDTO,Sensor.class);
        return sensor;
    }

}
