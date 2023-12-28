package com.ra.smarthome.services;

import com.ra.smarthome.models.Action;

import java.util.List;

public interface ActionService {

    List<Action> getAllActions();

    Action getActionById(Long actionId);

    Action createAction(Action newAction);

    Action updateAction(Long actionId, Action updateAction);

    void deleteAction(Long actionId);
}
