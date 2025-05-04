package com.ageof.war.common;

public enum PlatoonClass {
    MILITIA("Militia"),
    SPEARMEN("Spearmen"),
    LIGHT_CAVALRY("LightCavalry"),
    HEAVY_CAVALRY("HeavyCavalry"),
    FOOT_ARCHER("FootArcher"),
    CAVALRY_ARCHER("CavalryArcher");

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    PlatoonClass(String name){
        this.name = name;
    }
}
