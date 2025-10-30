package ru.hakaton.portvision.service;

import org.springframework.stereotype.Service;
import ru.hakaton.portvision.dto.Port;
import ru.hakaton.portvision.util.Calculator;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataExtractor {

    private final Calculator calculator = new Calculator();

    public Port extractPort(String data, Port port){

        if (port.getAirPollution() != null) {
            String airPollution;
            if (data.contains("AP")) {
                airPollution = data.substring(data.indexOf("A"));
            } else {
                airPollution = null;
            }
            List<Double> newAir = port.getAirPollution().stream()
                    .map(e -> getValue(e == null ? Double.parseDouble(airPollution) : e))
                    .collect(Collectors.toList());
            port.setAirPollution(newAir);
        }

        if (port.getWaterPollution() != null) {
            String waterPollution;
            if (data.contains("WP")) {
                waterPollution = data.substring(data.indexOf("W"));
            } else {
                waterPollution = null;
            }
            List<Double> newWater = port.getWaterPollution().stream()
                    .map(e -> getValue(e == null ? Double.parseDouble(waterPollution) : e))
                    .collect(Collectors.toList());
            port.setWaterPollution(newWater);
        }

        return port;
    }

    private Double getValue(Double value) {
        double percentChange = (calculator.calculate(value) * 0.1) - 0.02;
        return Math.round(value * (1 + percentChange) * 100.0) / 100.0;
    }
}
