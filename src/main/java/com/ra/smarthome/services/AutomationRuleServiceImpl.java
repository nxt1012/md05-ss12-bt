package com.ra.smarthome.services;

import com.ra.smarthome.exceptions.ResourceNotFoundException;
import com.ra.smarthome.models.AutomationRule;
import com.ra.smarthome.repositories.AutomationRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutomationRuleServiceImpl implements AutomationRuleService {

    @Autowired
    private AutomationRuleRepository automationRuleRepository;

    @Override
    public List<AutomationRule> getAllAutomationRules() {
        return automationRuleRepository.findAll();
    }

    @Override
    public AutomationRule getAutomationRuleById(Long ruleId) {
        return automationRuleRepository.findById(ruleId)
                .orElseThrow(() -> new ResourceNotFoundException("Automation Rule not found with id: " + ruleId));
    }

    @Override
    public AutomationRule createAutomationRule(AutomationRule newAutomationRule) {
        // TODO: Add validation or business logic
        return automationRuleRepository.save(newAutomationRule);
    }

    @Override
    public AutomationRule updateAutomationRule(Long ruleId, AutomationRule updateAutomationRule) {
        // Check if the rule exists before updating
        AutomationRule existingRule = getAutomationRuleById(ruleId);
        // TODO: Update rule properties
        return automationRuleRepository.save(existingRule);
    }

    @Override
    public void deleteAutomationRule(Long ruleId) {
        // Check if the rule exists before deleting
        automationRuleRepository.findById(ruleId)
                .orElseThrow(() -> new ResourceNotFoundException("Automation Rule not found with id: " + ruleId));
        automationRuleRepository.deleteById(ruleId);
    }
}
