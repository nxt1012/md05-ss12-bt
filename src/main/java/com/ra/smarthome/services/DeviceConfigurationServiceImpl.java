package com.ra.smarthome.services;

import com.ra.smarthome.exceptions.ResourceNotFoundException;
import com.ra.smarthome.models.configurations.DeviceConfiguration;
import com.ra.smarthome.repositories.DeviceConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceConfigurationServiceImpl implements DeviceConfigurationService {

    @Autowired
    private DeviceConfigurationRepository deviceConfigurationRepository;

    @Override
    public List<DeviceConfiguration> getAllDeviceConfigurations() {
        return deviceConfigurationRepository.findAll();
    }

    @Override
    public DeviceConfiguration getDeviceConfigurationById(Long configurationId) {
        return deviceConfigurationRepository.findById(configurationId)
                .orElseThrow(() -> new ResourceNotFoundException("Device Configuration not found with id: " + configurationId));
    }

    @Override
    public DeviceConfiguration createDeviceConfiguration(DeviceConfiguration newDeviceConfiguration) {
        // TODO: Add validation or business logic
        return deviceConfigurationRepository.save(newDeviceConfiguration);
    }

    @Override
    public DeviceConfiguration updateDeviceConfiguration(Long configurationId, DeviceConfiguration updateDeviceConfiguration) {
        // Check if the configuration exists before updating
        DeviceConfiguration existingConfiguration = getDeviceConfigurationById(configurationId);
        // TODO: Update configuration properties
        return deviceConfigurationRepository.save(existingConfiguration);
    }

    @Override
    public void deleteDeviceConfiguration(Long configurationId) {
        // Check if the configuration exists before deleting
        deviceConfigurationRepository.findById(configurationId)
                .orElseThrow(() -> new ResourceNotFoundException("Device Configuration not found with id: " + configurationId));
        deviceConfigurationRepository.deleteById(configurationId);
    }
}
