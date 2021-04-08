package com.forgerock.core.devicefeatures.domain;

import org.springframework.stereotype.Component;

public class DeviceModel {
    public String eventId;
    public Device device;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}