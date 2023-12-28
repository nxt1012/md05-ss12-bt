package com.ra.smarthome.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccessPermission {
    private boolean canControl;
    private boolean canAdjustSettings;
}
