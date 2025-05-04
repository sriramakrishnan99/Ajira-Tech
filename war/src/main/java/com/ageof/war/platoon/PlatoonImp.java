package com.ageof.war.platoon;

import com.ageof.war.common.PlatoonClass;

import java.util.Set;

public class PlatoonImp implements Platoon {
    private String name;
    private Set<PlatoonClass> advantageOver;

    public PlatoonImp(String name){
        this.name = name;
    }

    public PlatoonImp(String name, Set<PlatoonClass> advantageOver){
        this.name = name;
        this.advantageOver = advantageOver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PlatoonClass> getAdvantageOver() {
        return advantageOver;
    }

    public void setAdvantageOver(Set<PlatoonClass> advantageOver) {
        this.advantageOver = advantageOver;
    }

    @Override
    public String toString() {
        return "{" +
        "name='" + name + '\'' +
        ", advantageOver=" + advantageOver +
        '}';
    }
}
