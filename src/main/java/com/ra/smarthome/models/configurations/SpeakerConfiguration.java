package com.ra.smarthome.models.configurations;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("Speaker")
public class SpeakerConfiguration extends DeviceConfiguration {
    private int volume;
    private String soundMode;
}
