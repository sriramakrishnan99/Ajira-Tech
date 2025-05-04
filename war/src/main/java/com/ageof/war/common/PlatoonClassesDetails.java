package com.ageof.war.common;

import com.ageof.war.platoon.PlatoonImp;

import java.util.List;
import java.util.Map;

public class PlatoonClassesDetails {
    public List<Map.Entry<PlatoonClass, PlatoonImp>> getDetails() {
        return details;
    }

    public void setDetails(List<Map.Entry<PlatoonClass, PlatoonImp>> details) {
        this.details = details;
    }

    private List<Map.Entry<PlatoonClass, PlatoonImp>> details;

    public PlatoonClassesDetails(List<Map.Entry<PlatoonClass, PlatoonImp>> details) {
        this.details = details;
    }

}
