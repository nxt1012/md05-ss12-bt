package com.ra.smarthome.controllers;

import com.ra.smarthome.exceptions.UnauthorizedAccessException;
import com.ra.smarthome.models.AccessPermission;
import com.ra.smarthome.models.Room;
import com.ra.smarthome.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long roomId) {
        Room room = roomService.getRoomById(roomId);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        Room createdRoom = roomService.createRoom(room);
        return new ResponseEntity<>(createdRoom, HttpStatus.CREATED);
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long roomId, @RequestBody Room room) {
        Room updatedRoom = roomService.updateRoom(roomId, room);
        return new ResponseEntity<>(updatedRoom, HttpStatus.OK);
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId) {
        roomService.deleteRoom(roomId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //    MAIN FUNCTIONALITIES
//    Manage rooms
// Check User Permissions for a Room
    @GetMapping("/{roomId}/check-user-permissions")
    public ResponseEntity<String> checkUserPermissions(
            @PathVariable Long roomId, @RequestParam Long userId) {
        if (roomService.hasPermissionToAccessRoom(userId, roomId)) {
            return ResponseEntity.ok("User has permission to access the room");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User does not have permission to access the room");
        }
    }

    // Manage devices in a room
    // Add Device to Room
    @PostMapping("/{roomId}/add-device/{deviceId}")
    public ResponseEntity<String> addDeviceToRoom(
            @PathVariable Long roomId, @PathVariable Long deviceId, @RequestParam Long userId) {
        try {
            roomService.addDeviceToRoom(deviceId, roomId, userId);
            return ResponseEntity.ok("Device added to room successfully");
        } catch (UnauthorizedAccessException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    // Set Device Access Permissions
    @PostMapping("/{roomId}/set-access-permissions/{deviceId}")
    public ResponseEntity<String> setDeviceAccessPermissions(
            @PathVariable Long roomId, @PathVariable Long deviceId, @RequestBody AccessPermission permission) {
        roomService.setDeviceAccessPermissions(deviceId, roomId, permission);
        return ResponseEntity.ok("Access permissions set successfully");
    }

}
