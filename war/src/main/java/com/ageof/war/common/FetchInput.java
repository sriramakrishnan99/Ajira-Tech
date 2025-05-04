package com.ageof.war.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FetchInput {
    @Value("${platoon.delimitter}")
    private String platoonDelimitter;
    @Value("${platoon.count.delimitter}")
    private String platoonAndItsCountDelimitter;

    public FetchInput(){}
    public FetchInput(String platoonDelimitter, String platoonAndItsCountDelimitter) {
        this.platoonDelimitter = platoonDelimitter;
        this.platoonAndItsCountDelimitter = platoonAndItsCountDelimitter;
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
}
