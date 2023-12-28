package com.ra.smarthome.repositories;

import com.ra.smarthome.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
