package com.ra.smarthome.models.configurations;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("Conditioner")
public class ConditionerConfiguration extends DeviceConfiguration {
    private boolean coolingMode;
    private int temperature;
}
