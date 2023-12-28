package com.ra.smarthome.models.conditions;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("Time")
class TimeCondition extends Condition {
    private int startHour;
    private int endHour;
}
