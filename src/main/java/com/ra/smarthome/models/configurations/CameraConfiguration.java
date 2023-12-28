package com.ra.smarthome.models.configurations;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("Camera")
public class CameraConfiguration extends DeviceConfiguration {
    private String recordingMode;
    private int resolution;
}
