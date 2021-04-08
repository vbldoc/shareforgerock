package com.forgerock.core.devicefeatures.domain;

import org.springframework.stereotype.Component;

public class FeaturingDevice {
    public String eventId;
    public String device_os;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getDevice_os() {
        return device_os;
    }

    public void setDevice_os(String device_os) {
        this.device_os = device_os;
    }
}
