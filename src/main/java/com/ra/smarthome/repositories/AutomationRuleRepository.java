package com.ra.smarthome.repositories;

import com.ra.smarthome.models.AutomationRule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutomationRuleRepository extends JpaRepository<AutomationRule, Long> {
}
