package com.forgerock.core.devicefeatures.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.forgerock.core.devicefeatures.domain.FeaturingDevice;
import com.forgerock.core.devicefeatures.domain.DeviceModel;
import com.forgerock.core.devicefeatures.services.DeviceService;
import com.schibsted.spt.data.jslt.Expression;
import com.schibsted.spt.data.jslt.Parser;
import com.schibsted.spt.data.jslt.impl.NodeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.StringReader;

@RestController
@RequestMapping(DeviceController.BASE_URL)
public class DeviceController {

    private final DeviceService deviceService;
    public static final String BASE_URL = "/api/v1/devices";

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    /**
     *
     * Post operation to allows transform a given DeviceModel into FeaturingDevice using jslt library.
     *
     * @param deviceModel
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/transformDevice", consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity <FeaturingDevice> postDevice(@RequestBody DeviceModel deviceModel) throws IOException {
        return getFeaturingDeviceResponseEntity(deviceModel);
    }

    /**
     *
     * Create a ResponseEntity with the given FeaturingDevice and HttoStatus code
     *
     * @param deviceModel
     * @return
     * @throws JsonProcessingException
     */
    private static ResponseEntity<FeaturingDevice> getFeaturingDeviceResponseEntity(DeviceModel deviceModel) throws JsonProcessingException {
        // Create base objects to do the jstl transformation process
        ObjectMapper mapper = new ObjectMapper();
        FeaturingDevice featuringDevice = new FeaturingDevice();
        String deviceInput = mapper.writeValueAsString(deviceModel);
        HttpStatus resolvedStatus = HttpStatus.OK;
        StringReader jslt = new StringReader(
                "{\"eventId\": .eventId, \"device_os\": .device.osType}"
        );
        JsonNode input = NodeUtils.mapper.readTree(deviceInput);
        try {
            Expression template = new Parser(jslt).compile();
            JsonNode output = template.apply(input);
            featuringDevice.setDevice_os(output.get("device_os").textValue());
            featuringDevice.setEventId(output.get("eventId").textValue());
        } catch (Exception e) {
            // Sanity check to return a client side bad request
            e.printStackTrace();
            resolvedStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(featuringDevice, resolvedStatus);
    }

}
