package com.ra.smarthome.services;

import com.ra.smarthome.exceptions.ResourceNotFoundException;
import com.ra.smarthome.models.conditions.Condition;
import com.ra.smarthome.repositories.ConditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConditionServiceImpl implements ConditionService {

    @Autowired
    private ConditionRepository conditionRepository;

    @Override
    public List<Condition> getAllConditions() {
        return conditionRepository.findAll();
    }

    @Override
    public Condition getConditionById(Long conditionId) {
        return conditionRepository.findById(conditionId)
                .orElseThrow(() -> new ResourceNotFoundException("Condition not found with id: " + conditionId));
    }

    @Override
    public Condition createCondition(Condition newCondition) {
        // TODO: Add validation or business logic
        return conditionRepository.save(newCondition);
    }

    @Override
    public Condition updateCondition(Long conditionId, Condition updateCondition) {
        // Check if the condition exists before updating
        Condition existingCondition = getConditionById(conditionId);
        // TODO: Update condition properties
        return conditionRepository.save(existingCondition);
    }

    @Override
    public void deleteCondition(Long conditionId) {
        // Check if the condition exists before deleting
        conditionRepository.findById(conditionId)
                .orElseThrow(() -> new ResourceNotFoundException("Condition not found with id: " + conditionId));
        conditionRepository.deleteById(conditionId);
    }
}
