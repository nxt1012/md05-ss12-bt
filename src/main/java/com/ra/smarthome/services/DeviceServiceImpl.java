package com.ra.smarthome.services;

import com.ra.smarthome.exceptions.ResourceNotFoundException;
import com.ra.smarthome.models.Device;
import com.ra.smarthome.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    @Override
    public Device getDeviceById(Long deviceId) {
        return deviceRepository.findById(deviceId).orElseThrow(() -> new ResourceNotFoundException("Device not found with id: " + deviceId));
    }

    @Override
    public Device createDevice(Device newDevice) {
        // TODO: Add validation or business logic
        return deviceRepository.save(newDevice);
    }

    @Override
    public Device updateDevice(Long deviceId, Device updateDevice) {
        // Check if the device exists before updating
        Device existingDevice = deviceRepository.findById(deviceId).orElseThrow(() -> new ResourceNotFoundException("Device not found with id: " + deviceId));
        // TODO: Update device properties
        existingDevice.setName(updateDevice.getName());
        existingDevice.setDescription(updateDevice.getDescription());
        existingDevice.setType(updateDevice.getType());
        return deviceRepository.save(existingDevice);
    }

    @Override
    public void deleteDevice(Long deviceId) {
        // Check if the device exists before deleting
        deviceRepository.findById(deviceId).orElseThrow(() -> new ResourceNotFoundException("Device not found with id: " + deviceId));
        deviceRepository.deleteById(deviceId);
    }
}
