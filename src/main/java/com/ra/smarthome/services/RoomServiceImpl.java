package com.ra.smarthome.services;

import com.ra.smarthome.exceptions.ResourceNotFoundException;
import com.ra.smarthome.exceptions.UnauthorizedAccessException;
import com.ra.smarthome.models.AccessPermission;
import com.ra.smarthome.models.Device;
import com.ra.smarthome.models.Room;
import com.ra.smarthome.models.User;
import com.ra.smarthome.repositories.DeviceRepository;
import com.ra.smarthome.repositories.RoomRepository;
import com.ra.smarthome.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoomById(Long roomId) {
        return roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + roomId));
    }

    @Override
    public Room createRoom(Room newRoom) {
        // TODO: Add validation or business logic
        return roomRepository.save(newRoom);
    }

    @Override
    public Room updateRoom(Long roomId, Room updateRoom) {
        // Check if the room exists before updating
        Room existingRoom = roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + roomId));
        // TODO: Update room properties
        return roomRepository.save(existingRoom);
    }

    @Override
    public void deleteRoom(Long roomId) {
        // Check if the room exists before deleting
        roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + roomId));
        roomRepository.deleteById(roomId);
    }
// MAIN FUNCTIONALITIES
    @Override
    public boolean hasPermissionToAccessRoom(Long userId, Long roomId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if (optionalUser.isPresent() && optionalRoom.isPresent()) {
            User user = optionalUser.get();
            Room room = optionalRoom.get();

            // Check if the user is granted to access the room or an admin
            return room.getUsers().contains(user) || user.getRole().name().equals("ROLE_ADMIN");
        }
        return false;
    }

    @Override
    public void addDeviceToRoom(Long deviceId, Long roomId, Long userId) {
        // Check if the user has permission to access the room
        if (!hasPermissionToAccessRoom(userId, roomId)) {
            throw new UnauthorizedAccessException("User does not have permission to access the room");
        }

        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        Optional<Device> optionalDevice = deviceRepository.findById(deviceId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalRoom.isPresent() && optionalDevice.isPresent() && optionalUser.isPresent()) {
            Room room = optionalRoom.get();
            Device device = optionalDevice.get();
            User user = optionalUser.get();

            // Check if the device already belongs to the room
            if (device.getRoom() != null && device.getRoom().getId().equals(roomId)) {
                throw new IllegalArgumentException("Device is already in the room");
            }

            // Add the device to the room
            device.setRoom(room);
            deviceRepository.save(device);

            // You might want to log this action or perform additional tasks
        } else {
            throw new ResourceNotFoundException("Room, Device, or User not found");
        }
    }

    @Override
    public void setDeviceAccessPermissions(Long deviceId, Long roomId, Long userId, AccessPermission permission) {
        // Check if the user has permission to access the room
        if (!hasPermissionToAccessRoom(userId, roomId)) {
            throw new UnauthorizedAccessException("User does not have permission to access the room");
        }

        Optional<Device> optionalDevice = deviceRepository.findById(deviceId);
        Optional<Room> optionalRoom = roomRepository.findById(roomId);

        if (optionalDevice.isPresent() && optionalRoom.isPresent()) {
            Device device = optionalDevice.get();
            Room room = optionalRoom.get();

            // Check if the device belongs to the room
            if (device.getRoom() == null || !device.getRoom().getId().equals(roomId)) {
                throw new IllegalArgumentException("Device does not belong to the specified room");
            }

            // Set access permissions for the device in the room
            device.setAccessPermission(permission);
            deviceRepository.save(device);

            // You might want to log this action or perform additional tasks
        } else {
            throw new ResourceNotFoundException("Device or Room not found");
        }
    }
}
