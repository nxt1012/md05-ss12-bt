package com.ra.smarthome.models.conditions;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("Temperature")
class TemperatureCondition extends Condition {
    private double minTemperature;
    private double maxTemperature;
}
