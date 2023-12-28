package com.ra.smarthome.services;

import com.ra.smarthome.exceptions.ResourceNotFoundException;
import com.ra.smarthome.models.Action;
import com.ra.smarthome.repositories.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionServiceImpl implements ActionService {

    @Autowired
    private ActionRepository actionRepository;

    @Override
    public List<Action> getAllActions() {
        return actionRepository.findAll();
    }

    @Override
    public Action getActionById(Long actionId) {
        return actionRepository.findById(actionId)
                .orElseThrow(() -> new ResourceNotFoundException("Action not found with id: " + actionId));
    }

    @Override
    public Action createAction(Action newAction) {
        // TODO: Add validation or business logic
        return actionRepository.save(newAction);
    }

    @Override
    public Action updateAction(Long actionId, Action updateAction) {
        // Check if the action exists before updating
        Action existingAction = getActionById(actionId);
        // TODO: Update action properties
        return actionRepository.save(existingAction);
    }

    @Override
    public void deleteAction(Long actionId) {
        // Check if the action exists before deleting
        actionRepository.findById(actionId)
                .orElseThrow(() -> new ResourceNotFoundException("Action not found with id: " + actionId));
        actionRepository.deleteById(actionId);
    }
}
