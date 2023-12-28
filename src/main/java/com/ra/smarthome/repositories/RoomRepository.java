package com.ra.smarthome.repositories;

import com.ra.smarthome.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
