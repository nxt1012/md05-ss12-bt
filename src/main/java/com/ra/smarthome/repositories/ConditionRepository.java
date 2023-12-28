package com.ra.smarthome.repositories;

import com.ra.smarthome.models.conditions.Condition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConditionRepository extends JpaRepository<Condition, Long> {
}
