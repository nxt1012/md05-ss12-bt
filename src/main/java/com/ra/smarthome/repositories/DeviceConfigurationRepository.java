package com.ra.smarthome.repositories;

import com.ra.smarthome.models.configurations.DeviceConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceConfigurationRepository extends JpaRepository<DeviceConfiguration, Long> {
}
