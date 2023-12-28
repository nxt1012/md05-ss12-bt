package com.ra.smarthome.services;

import com.ra.smarthome.models.AutomationRule;

import java.util.List;

public interface AutomationRuleService {

    List<AutomationRule> getAllAutomationRules();

    AutomationRule getAutomationRuleById(Long ruleId);

    AutomationRule createAutomationRule(AutomationRule newAutomationRule);

    AutomationRule updateAutomationRule(Long ruleId, AutomationRule updateAutomationRule);

    void deleteAutomationRule(Long ruleId);
}
