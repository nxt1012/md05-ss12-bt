package com.ra.smarthome.services;

import com.ra.smarthome.models.configurations.DeviceConfiguration;

import java.util.List;

public interface DeviceConfigurationService {

    List<DeviceConfiguration> getAllDeviceConfigurations();

    DeviceConfiguration getDeviceConfigurationById(Long configurationId);

    DeviceConfiguration createDeviceConfiguration(DeviceConfiguration newDeviceConfiguration);

    DeviceConfiguration updateDeviceConfiguration(Long configurationId, DeviceConfiguration updateDeviceConfiguration);

    void deleteDeviceConfiguration(Long configurationId);
}
