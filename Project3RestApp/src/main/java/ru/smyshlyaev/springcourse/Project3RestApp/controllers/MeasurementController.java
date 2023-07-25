package ru.smyshlyaev.springcourse.Project3RestApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.smyshlyaev.springcourse.Project3RestApp.dto.MeasurementDTO;
import ru.smyshlyaev.springcourse.Project3RestApp.models.Measurement;
import ru.smyshlyaev.springcourse.Project3RestApp.services.MeasurementService;
import ru.smyshlyaev.springcourse.Project3RestApp.util.DataErrorResponse;
import ru.smyshlyaev.springcourse.Project3RestApp.util.MeasurementValidator;
import ru.smyshlyaev.springcourse.Project3RestApp.util.measurementException.MeasurementNotCreatedException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;

@Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper, MeasurementValidator measurementValidator) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
    this.measurementValidator = measurementValidator;
}

@GetMapping
    List<MeasurementDTO> getMeasurements() {
    return measurementService.findAll().stream().map(this::convertToMeasurementDTO).collect(Collectors.toList());
    }

@GetMapping("/getRainyDayCount")
    public Integer getRainyDayCount() {
        return measurementService.findRaining();
    }

@PostMapping("/add")
    public ResponseEntity<HttpStatus> newMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                             BindingResult bindingResult) {

    Measurement addedMeasurement=convetrToMeasurement(measurementDTO);

        measurementValidator.validate(addedMeasurement,bindingResult);

        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> fieldErrors=bindingResult.getFieldErrors();

            for (FieldError error: fieldErrors ){
                errorMessage.append(error.getField())
                        .append("-").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new MeasurementNotCreatedException(errorMessage.toString());
        }

        measurementService.saveMeasurement(addedMeasurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler //
    private ResponseEntity<DataErrorResponse> handleException(MeasurementNotCreatedException e) {
        DataErrorResponse response = new DataErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    private Measurement convetrToMeasurement(MeasurementDTO measurementDTO) { //метод конвертации
        Measurement measurement=modelMapper.map(measurementDTO,Measurement.class);
        return measurement;
    }

    private MeasurementDTO convertToMeasurementDTO (Measurement measurement) { //метод конвертации
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

}
