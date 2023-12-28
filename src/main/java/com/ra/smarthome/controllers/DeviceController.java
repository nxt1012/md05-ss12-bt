package com.ra.smarthome.controllers;

import com.ra.smarthome.models.Device;
import com.ra.smarthome.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public ResponseEntity<List<Device>> getAllDevices() {
        List<Device> devices = deviceService.getAllDevices();
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }

    @GetMapping("/{deviceId}")
    public ResponseEntity<Device> getDeviceById(@PathVariable Long deviceId) {
        Device device = deviceService.getDeviceById(deviceId);
        return new ResponseEntity<>(device, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Device> createDevice(@RequestBody Device device) {
        Device createdDevice = deviceService.createDevice(device);
        return new ResponseEntity<>(createdDevice, HttpStatus.CREATED);
    }

    @PutMapping("/{deviceId}")
    public ResponseEntity<Device> updateDevice(@PathVariable Long deviceId, @RequestBody Device device) {
        Device updatedDevice = deviceService.updateDevice(deviceId, device);
        return new ResponseEntity<>(updatedDevice, HttpStatus.OK);
    }

    @DeleteMapping("/{deviceId}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long deviceId) {
        deviceService.deleteDevice(deviceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // LIGHTS
    // Toggle Light
    @PostMapping("/lights/toggle/{deviceId}")
    public ResponseEntity<String> toggleLight(@PathVariable Long deviceId) {
        deviceService.toggleLight(deviceId);
        return ResponseEntity.ok("Light toggled successfully");
    }

    // Adjust Brightness
    @PostMapping("/lights/adjust-brightness/{deviceId}")
    public ResponseEntity<String> adjustBrightness(@PathVariable Long deviceId, @RequestParam int brightness) {
        deviceService.adjustBrightness(deviceId, brightness);
        return ResponseEntity.ok("Brightness adjusted successfully");
    }
    // TV
    // Toggle TV
    @PostMapping("/tv/toggle/{deviceId}")
    public ResponseEntity<String> toggleTV(@PathVariable Long deviceId) {
        deviceService.toggleTV(deviceId);
        return ResponseEntity.ok("TV toggled successfully");
    }

    // Adjust TV Volume
    @PostMapping("/tv/adjust-volume/{deviceId}")
    public ResponseEntity<String> adjustTVVolume(@PathVariable Long deviceId, @RequestParam int volume) {
        deviceService.adjustTVVolume(deviceId, volume);
        return ResponseEntity.ok("TV volume adjusted successfully");
    }

    @PostMapping("/tv/change-channel/{deviceId}")
    public ResponseEntity<String> changeTVChannel(@PathVariable Long deviceId, @RequestParam int channel) {
        deviceService.changeTVChannel(deviceId, channel);
        return ResponseEntity.ok("TV channel changed successfully");
    }

    // SPEAKER
    // Toggle Speaker
    @PostMapping("/speaker/toggle/{deviceId}")
    public ResponseEntity<String> toggleSpeaker(@PathVariable Long deviceId) {
        deviceService.toggleSpeaker(deviceId);
        return ResponseEntity.ok("Speaker toggled successfully");
    }
    // Adjust Speaker Volume
    @PostMapping("/speaker/adjust-volume/{deviceId}")
    public ResponseEntity<String> adjustSpeakerVolume(@PathVariable Long deviceId, @RequestParam int volume) {
        deviceService.adjustSpeakerVolume(deviceId, volume);
        return ResponseEntity.ok("Speaker volume adjusted successfully");
    }

    // BLINDS
    // Toggle Blinds
    @PostMapping("/blinds/toggle/{deviceId}")
    public ResponseEntity<String> toggleBlinds(@PathVariable Long deviceId) {
        deviceService.toggleBlinds(deviceId);
        return ResponseEntity.ok("Blinds toggled successfully");
    }

    // Adjust Blinds position
    @PostMapping("/blinds/adjust-position/{deviceId}")
    public ResponseEntity<String> adjustBlindsPosition(@PathVariable Long deviceId, @RequestParam int position) {
        deviceService.adjustBlindsPosition(deviceId, position);
        return ResponseEntity.ok("Blinds position adjusted successfully");
    }

    // AIR CONDITIONER
    // Toggle Air Conditioner
    @PostMapping("/air-conditioner/toggle/{deviceId}")
    public ResponseEntity<String> toggleAirConditioner(@PathVariable Long deviceId) {
        deviceService.toggleAirConditioner(deviceId);
        return ResponseEntity.ok("Air Conditioner toggled successfully");
    }

    // Adjust Air Conditioner Temperature
    @PostMapping("/air-conditioner/adjust-temperature/{deviceId}")
    public ResponseEntity<String> adjustAirConditionerTemperature(@PathVariable Long deviceId, @RequestParam int temperature) {
        deviceService.adjustAirConditionerTemperature(deviceId, temperature);
        return ResponseEntity.ok("Air Conditioner temperature adjusted successfully");
    }

    // CAMERA
    // Toggle Camera
    @PostMapping("/camera/toggle/{deviceId}")
    public ResponseEntity<String> toggleCamera(@PathVariable Long deviceId) {
        deviceService.toggleCamera(deviceId);
        return ResponseEntity.ok("Camera toggled successfully");
    }
    // Start Camera Recording
    @PostMapping("/camera/start-recording/{deviceId}")
    public ResponseEntity<String> startCameraRecording(@PathVariable Long deviceId) {
        deviceService.startCameraRecording(deviceId);
        return ResponseEntity.ok("Camera recording started successfully");
    }
    // Stop Camera Recording
    @PostMapping("/camera/stop-recording/{deviceId}")
    public ResponseEntity<String> stopCameraRecording(@PathVariable Long deviceId) {
        deviceService.stopCameraRecording(deviceId);
        return ResponseEntity.ok("Camera recording stopped successfully");
    }



}
