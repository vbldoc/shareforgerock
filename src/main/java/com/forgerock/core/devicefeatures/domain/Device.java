package com.forgerock.core.devicefeatures.domain;

import org.springframework.stereotype.Component;

public class Device{
    public String osType;
    public String model;

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
