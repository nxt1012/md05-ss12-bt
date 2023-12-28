package com.ra.smarthome.models.configurations;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("Blinds")
public class BlindsConfiguration extends DeviceConfiguration {
    private boolean automaticMode;
    private int positionLevel;
}
