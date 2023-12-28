package com.ra.smarthome.repositories;

import com.ra.smarthome.models.Scene;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SceneRepository extends JpaRepository<Scene, Long> {
}
