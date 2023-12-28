package com.ra.smarthome.repositories;

import com.ra.smarthome.models.Action;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<Action, Long> {
}
