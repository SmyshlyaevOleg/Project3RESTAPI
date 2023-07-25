import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Consumer {

    public static void main(String[] args) {

        String sensorName="sensor 10";
        registerSensor(sensorName);

        Random random=new Random();

        double minTemperature = 0.0;
        double maxTemperature = 50.0;
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
            sendMeasurementValue(random.nextDouble() * maxTemperature, random.nextBoolean(), sensorName);

        }
    }

        //метод для регистрации нового сенсора
    public static Object sensorData(String sensorName) {

        Map<String,Object> sensorData=new HashMap<String, Object>();
        sensorData.put("sensorName",sensorName);

        return sensorData;
    }

    public static void registerSensor(String sensorName) {
        String url = "http://localhost:8080/sensor/registration";

        Map<String,Object> jsonSensorData=new HashMap<String, Object>();
        jsonSensorData.put("sensorName",sensorName);

        sendPostRequestWithJSONData(url,jsonSensorData);

    }
    public static void sendMeasurementValue(Double value, Boolean raining, String sensorName) {
        String url = "http://localhost:8080/measurement/add";
        Map<String,Object> jsonMeasurementValues=new HashMap<String, Object>();
        jsonMeasurementValues.put("value",value );
        jsonMeasurementValues.put("raining", raining);
        jsonMeasurementValues.put("sourceSensor",sensorData(sensorName));

        sendPostRequestWithJSONData(url,jsonMeasurementValues);

    }

        // метод для отправки данных на сервер - принимается адрес и Мапа с данными для отправки на сервер
    public static void sendPostRequestWithJSONData(String url, Map<String,Object> jsonData) {

        final RestTemplate restTemplate=new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String,Object>> request= new HttpEntity<>(jsonData,headers);
    try {
        restTemplate.postForEntity(url,request,String.class);
        System.out.println("Измерение успешно отправлено на сервер");
    }
    catch (HttpClientErrorException e) {
        System.out.println("Ошибка");
        System.out.println(e.getMessage());
    }

    }

}
