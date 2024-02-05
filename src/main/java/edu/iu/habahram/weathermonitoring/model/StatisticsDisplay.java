package edu.iu.habahram.weathermonitoring.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class StatisticsDisplay implements Observer, DisplayElement {
    private final List<Float> temperatures = new ArrayList<>();
    private final String name = "Weather Stats";
    private final String id = "weather-stats";

    @Override
    public void update(float temperature, float humidity, float pressure) {
        temperatures.add(temperature);
    }

    @Override
    public String name() {
        return "Statistics Display";
    }

    @Override
    public String id() {
        return "statistics";
    }

    @Override
    public String display() {
        Float avgTemp = (float) temperatures.stream().mapToDouble(temp -> temp).average().orElse(0f);
        Float minTemp = temperatures.stream().min(Comparator.naturalOrder()).orElse(0f);
        Float maxTemp = temperatures.stream().max(Comparator.naturalOrder()).orElse(0f);

        String html = "<div>";
        html += "<h2>" + name + "</h2>";
        html += "<p>Avg. temp: " + avgTemp + "</p>";
        html += "<p>Min. temp: " + minTemp + "</p>";
        html += "<p>Max temp: " + maxTemp + "</p>";
        html += "</div>";

        return html;
    }
}
