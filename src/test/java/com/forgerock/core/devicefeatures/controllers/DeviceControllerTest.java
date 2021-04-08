package com.forgerock.core.devicefeatures.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forgerock.core.devicefeatures.domain.Device;
import com.forgerock.core.devicefeatures.domain.DeviceModel;
import com.forgerock.core.devicefeatures.services.DeviceService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DeviceController.class)
@WithMockUser
class DeviceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeviceService deviceService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void postDevice() throws Exception {

        Device device = new Device();
        device.setModel("NA");
        device.setOsType("Linux");
        DeviceModel mockDeviceModel = new DeviceModel();
        mockDeviceModel.setDevice(device);
        mockDeviceModel.setEventId("1234");

        ObjectMapper mapper = new ObjectMapper();
        Mockito.when(deviceService.postDevice(Mockito.any(DeviceModel.class))).thenReturn(mockDeviceModel);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/v1/devices/transformDevice")
                .accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(mockDeviceModel))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        System.out.println(response.getContentAsString());

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(response.getContentAsString(), "{\"eventId\":\"1234\",\"device_os\":\"Linux\"}");
    }
}