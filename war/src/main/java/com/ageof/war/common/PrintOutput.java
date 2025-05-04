package com.ageof.war.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PrintOutput {
    private List<Map.Entry<String, Integer>> output;
    @Value("${platoon.delimitter}")
    private String platoonDelimitter;
    @Value("${platoon.count.delimitter}")
    private String platoonAndItsCountDelimitter;

    public PrintOutput(){}

    public PrintOutput(List<Map.Entry<String, Integer>> output) {
        this.output = output;
    }

    public PrintOutput(String platoonDelimitter, String platoonAndItsCountDelimitter, List<Map.Entry<String, Integer>> output) {
        this.output = output;
        this.platoonDelimitter = platoonDelimitter;
        this.platoonAndItsCountDelimitter = platoonAndItsCountDelimitter;
    }

    public List<Map.Entry<String, Integer>> getOutput() {
        return output;
    }

    public void setOutput(List<Map.Entry<String, Integer>> output) {
        this.output = output;
    }

    public String getPlatoonDelimitter() {
        return platoonDelimitter;
    }

    public void setPlatoonDelimitter(String platoonDelimitter) {
        this.platoonDelimitter = platoonDelimitter;
    }

    public String getPlatoonAndItsCountDelimitter() {
        return platoonAndItsCountDelimitter;
    }

    public void setPlatoonAndItsCountDelimitter(String platoonAndItsCountDelimitter) {
        this.platoonAndItsCountDelimitter = platoonAndItsCountDelimitter;
    }

    @Override
    public String toString() {
        return
        output
            .stream()
            .map(entry -> entry.getKey() + platoonAndItsCountDelimitter + entry.getValue())
            .collect(Collectors.joining(platoonDelimitter));
    }
}

