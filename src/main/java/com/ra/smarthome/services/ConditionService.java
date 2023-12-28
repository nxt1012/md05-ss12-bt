package com.ra.smarthome.services;

import com.ra.smarthome.models.conditions.Condition;

import java.util.List;

public interface ConditionService {

    List<Condition> getAllConditions();

    Condition getConditionById(Long conditionId);

    Condition createCondition(Condition newCondition);

    Condition updateCondition(Long conditionId, Condition updateCondition);

    void deleteCondition(Long conditionId);
}
