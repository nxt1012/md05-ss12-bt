package com.ra.smarthome.services;

import com.ra.smarthome.models.Device;

import java.util.List;

public interface DeviceService {
    List<Device> getAllDevices();
    Device getDeviceById(Long deviceId);
    Device createDevice(Device newDevice);
    Device updateDevice(Long deviceId, Device updateDevice);
    void deleteDevice(Long deviceId);
//    MAIN FUNCTIONALITIES
    void toggleLight(Long deviceId);

    void adjustBrightness(Long deviceId, int brightness);

    void toggleTV(Long deviceId);

    void adjustTVVolume(Long deviceId, int volume);

    void changeTVChannel(Long deviceId, int channel);

    void toggleSpeaker(Long deviceId);

    void adjustSpeakerVolume(Long deviceId, int volume);

    void toggleBlinds(Long deviceId);

    void adjustBlindsPosition(Long deviceId, int position);

    void toggleAirConditioner(Long deviceId);

    void adjustAirConditionerTemperature(Long deviceId, int temperature);

    void toggleCamera(Long deviceId);

    void startCameraRecording(Long deviceId);

    void stopCameraRecording(Long deviceId);
}
