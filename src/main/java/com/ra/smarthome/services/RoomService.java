package com.ra.smarthome.services;

import com.ra.smarthome.models.Room;
import com.ra.smarthome.models.AccessPermission;

import java.util.List;

public interface RoomService {
    List<Room> getAllRooms();

    Room getRoomById(Long roomId);

    Room createRoom(Room newRoom);

    Room updateRoom(Long roomId, Room updateRoom);

    void deleteRoom(Long roomId);

    // MAIN FUNCTIONALITIES
    boolean hasPermissionToAccessRoom(Long userId, Long roomId);

    void addDeviceToRoom(Long deviceId, Long roomId, Long userId);

    void setDeviceAccessPermissions(Long deviceId, Long roomId, AccessPermission permission);

    void setDeviceAccessPermissions(Long deviceId, Long roomId, Long userId, AccessPermission permission);
}
